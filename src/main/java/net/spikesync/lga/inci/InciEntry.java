package net.spikesync.lga.inci;

public class InciEntry {

    private final int refNo;           // COSING reference number
    private final String inciName;     // INCI NAME
    private final String innName;      // INN name
    private final String phEurName;    // Pharmacopoeia (EU)
    private final String casNo;        // CAS registry number(s)
    private final String ecNo;         // EC number
    private final String description;  // IUPAC / chemical description
    private final String restriction;  // EU restrictions (if applicable)
    private final String function;     // SKIN CONDITIONING etc.
    private final String updateDate;   // dd/MM/yyyy

    public InciEntry(int refNo,
                     String inciName,
                     String innName,
                     String phEurName,
                     String casNo,
                     String ecNo,
                     String description,
                     String restriction,
                     String function,
                     String updateDate) {

        this.refNo = refNo;
        this.inciName = inciName;
        this.innName = innName;
        this.phEurName = phEurName;
        this.casNo = casNo;
        this.ecNo = ecNo;
        this.description = description;
        this.restriction = restriction;
        this.function = function;
        this.updateDate = updateDate;
    }

    public int getRefNo() { return refNo; }
    public String getInciName() { return inciName; }
    public String getInnName() { return innName; }
    public String getPhEurName() { return phEurName; }
    public String getCasNo() { return casNo; }
    public String getEcNo() { return ecNo; }
    public String getDescription() { return description; }
    public String getRestriction() { return restriction; }
    public String getFunction() { return function; }
    public String getUpdateDate() { return updateDate; }
}

