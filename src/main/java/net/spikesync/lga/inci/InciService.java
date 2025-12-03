package net.spikesync.lga.inci;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Service
public class InciService {

    // Main lookup map: INCI name (uppercase, normalized) → entry
    private final Map<String, InciEntry> inciMap = new HashMap<>();


    // -------------------------------------------------------
    //  Public API
    // -------------------------------------------------------

    /** Check if INCI ingredient exists */
    public boolean exists(String ingredient) {
        return inciMap.containsKey(normalize(ingredient));
    }

    /** Retrieve full entry (or null) */
    public InciEntry get(String ingredient) {
        return inciMap.get(normalize(ingredient));
    }

    /** List of all known INCI entries */
    public Collection<InciEntry> getAll() {
        return inciMap.values();
    }


    // -------------------------------------------------------
    //  Startup: Load CSV (called automatically on startup)
    // -------------------------------------------------------

    @PostConstruct
    public void loadCsv() {

        System.out.println(">>> InciService: loading INCI CSV…");

        InputStream is = openCsv();

        if (is == null) {
            System.err.println("### ERROR: inci.csv NOT FOUND in resources!");
            return;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8))) {

            List<String> lines = reader.lines().toList();
            System.out.println(">>> CSV lines found: " + lines.size());

            int startIndex = findRealHeaderStart(lines);

            for (int i = startIndex; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue;

                List<String> columns = parseCsvLine(line);

                if (columns.size() < 10) continue;

                InciEntry entry = new InciEntry(
                        parseInt(columns.get(0)),      // Ref No
                        normalize(columns.get(1)),     // INCI name
                        normalize(columns.get(2)),     // INN name
                        normalize(columns.get(3)),     // Ph Eur Name
                        normalize(columns.get(4)),     // CAS No
                        normalize(columns.get(5)),     // EC No
                        normalize(columns.get(6)),     // Description
                        normalize(columns.get(7)),     // Restriction
                        normalize(columns.get(8)),     // Function
                        normalize(columns.get(9))      // Update Date
                );

                // Use INCI name as lookup key
                String key = normalize(columns.get(1));
                inciMap.put(key, entry);
            }

            System.out.println(">>> Loaded INCI entries: " + inciMap.size());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception ex) {
            return -1;
        }
    }


    // -------------------------------------------------------
    //  Locate CSV file in classpath (robust)
    // -------------------------------------------------------

    private InputStream openCsv() {

        // 1. Try "src/main/resources/inci.csv"
        InputStream is = getClass().getResourceAsStream("/inci.csv");

        // 2. Try alternate class loader
        if (is == null) {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("inci.csv");
        }

        // 3. Try packaged JAR ("BOOT-INF/classes/inci.csv")
        if (is == null) {
            is = InciService.class.getClassLoader().getResourceAsStream("inci.csv");
        }

        if (is != null) {
            System.out.println("? INCI CSV found in classpath.");
        }

        return is;
    }


    // -------------------------------------------------------
    //  CSV Parsing helpers
    // -------------------------------------------------------

    /** Find where the real data begins (skip metadata header block) */
    private int findRealHeaderStart(List<String> lines) {
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("COSING Ref No")) {
                return i + 1; // data starts after header row
            }
        }
        // fallback
        return 8;
    }

    /** Normalize names: trim + collapse spaces + uppercase */
    private String normalize(String s) {
        if (s == null) return "";
        return s
                .trim()
                .replaceAll("\\s+", " ")
                .toUpperCase(Locale.ROOT);
    }

    /** Parse CSV lines including quoted fields with commas */
    private List<String> parseCsvLine(String line) {
        List<String> cols = new ArrayList<>();
        boolean inQuotes = false;
        StringBuilder current = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                cols.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        cols.add(current.toString());

        return cols;
    }
    
    /**
     * Validate a user-entered cosmetic ingredient against the INCI database.
     * Returns true if the ingredient exists (after normalization).
     */
    public boolean isValid(String ingredient) {

        if (ingredient == null) {
            return false;
        }

        // Normalize input
        String key = normalize(ingredient);

        if (key.isEmpty()) {
            return false;
        }

        // Direct match
        if (inciMap.containsKey(key)) {
            return true;
        }

        // Optional: handle common synonyms
        if (key.equals("WATER")) return inciMap.containsKey("AQUA");

        return false;
    }

}
