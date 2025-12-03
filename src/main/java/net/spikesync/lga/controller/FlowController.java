package net.spikesync.lga.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import net.spikesync.lga.model.*;
import net.spikesync.lga.inci.InciService;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@Slf4j
@SessionAttributes("sessionData")
public class FlowController {

    private final InciService inciService;

    public FlowController(InciService inciService) {
        this.inciService = inciService;
    }

    @ModelAttribute("sessionData")
    public LabelGenerationSessionData initSession() {
        return new LabelGenerationSessionData();
    }

    // -------------------------------------------------------------
    // HOME
    // -------------------------------------------------------------
    @GetMapping("/")
    public String home() {
        return "home";
    }

    // -------------------------------------------------------------
    // STEP 1 — COUNTRY
    // -------------------------------------------------------------
    @GetMapping("/country")
    public String showCountry(Model model) {
        model.addAttribute("countryForm", new CountryForm());
        return "country";
    }

    @PostMapping("/country")
    public String handleCountry(
            @Valid @ModelAttribute("countryForm") CountryForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data,
            Model model
    ) {

        if (validation.hasErrors()) {
            return "country";
        }

        // Allowed values
        Set<String> allowed = Set.of(
                "FRANCE", "GERMANY", "ITALY", "SPAIN", "NETHERLANDS"
        );

        if (!allowed.contains(form.getSelectedCountry())) {
            validation.rejectValue("selectedCountry", "invalid", "Please select a valid country.");
            return "country";
        }

        data.setCountryForm(form);
        return "redirect:/product";
    }

    // -------------------------------------------------------------
    // STEP 2 — PRODUCT
    // -------------------------------------------------------------
    @GetMapping("/product")
    public String showProduct(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product";
    }

    @PostMapping("/product")
    public String handleProduct(
            @Valid @ModelAttribute("productForm") ProductForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data
    ) {

        if (validation.hasErrors())
            return "product";

        data.setProductForm(form);
        return "redirect:/product-details";
    }

    // -------------------------------------------------------------
    // STEP 3 — PRODUCT DETAILS
    // -------------------------------------------------------------
    @GetMapping("/product-details")
    public String showDetails(Model model) {
        model.addAttribute("productDetailsForm", new ProductDetailsForm());
        return "productDetails";
    }

    @PostMapping("/product-details")
    public String handleDetails(
            @Valid @ModelAttribute("productDetailsForm") ProductDetailsForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data
    ) {

        if (validation.hasErrors())
            return "productDetails";

        data.setProductDetailsForm(form);
        return "redirect:/ingredients";
    }

    // -------------------------------------------------------------
    // STEP 4 — INGREDIENTS
    // -------------------------------------------------------------
    @GetMapping("/ingredients")
    public String showIngredients(Model model) {
        model.addAttribute("ingredientForm", new IngredientForm());
        return "ingredients";
    }

    @PostMapping("/ingredients")
    public String handleIngredients(
            @Valid @ModelAttribute("ingredientForm") IngredientForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data
    ) {

        if (validation.hasErrors())
            return "ingredients";

        // validate INCI entries
        for (String ing : form.getIngredients().split(",")) {
            String normalized = ing.trim();
            if (!normalized.isBlank() && !inciService.isValid(normalized)) {
                validation.rejectValue("ingredients", "invalid", "Unknown INCI: " + normalized);
                return "ingredients";
            }
        }

        data.setIngredientForm(form);
        return "redirect:/custom-size";
    }

    // -------------------------------------------------------------
    // STEP 5 — CUSTOM SIZE
    // -------------------------------------------------------------
    @GetMapping("/custom-size")
    public String showSize(Model model) {
        model.addAttribute("sizeForm", new SizeForm());
        return "customSize";
    }

    @PostMapping("/custom-size")
    public String handleSize(
            @Valid @ModelAttribute("sizeForm") SizeForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data
    ) {

        if (validation.hasErrors())
            return "customSize";

        data.setSizeForm(form);
        return "redirect:/logo-generation";
    }

    // -------------------------------------------------------------
    // STEP 6 — LOGO SELECTION
    // -------------------------------------------------------------
    @GetMapping("/logo-generation")
    public String showLogos(
            @ModelAttribute("sessionData") LabelGenerationSessionData data,
            Model model
    ) {
        LogoForm lf = new LogoForm();

        boolean isFrance = "FRANCE".equalsIgnoreCase(
                data.getCountryForm().getSelectedCountry()
        );

        model.addAttribute("logoForm", lf);
        model.addAttribute("showTriman", isFrance);

        return "logoGeneration";
    }

    @PostMapping("/logo-generation")
    public String handleLogo(
            @Valid @ModelAttribute("logoForm") LogoForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data,
            Model model
    ) {

        boolean isFrance = "FRANCE".equalsIgnoreCase(
                data.getCountryForm().getSelectedCountry()
        );

        model.addAttribute("showTriman", isFrance);

        if (!isFrance)
            form.setTrimanIncluded(false);

        if (validation.hasErrors())
            return "logoGeneration";

        data.setLogoForm(form);
        return "redirect:/file-upload";
    }

    // -------------------------------------------------------------
    // STEP 7 — FILE UPLOAD
    // -------------------------------------------------------------
    @GetMapping("/file-upload")
    public String showUpload(Model model) {
        model.addAttribute("fileUploadForm", new FileUploadForm());
        return "fileUpload";
    }

    @PostMapping("/file-upload")
    public String handleUpload(
            @Valid @ModelAttribute("fileUploadForm") FileUploadForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data
    ) {

        if (validation.hasErrors())
            return "fileUpload";

        data.setFileUploadForm(form);
        return "redirect:/pricing";
    }

    // -------------------------------------------------------------
    // STEP 8 — PRICING
    // -------------------------------------------------------------
    @GetMapping("/pricing")
    public String showPricing(Model model) {
        model.addAttribute("pricingForm", new PricingForm());
        return "pricing";
    }

    @PostMapping("/pricing")
    public String handlePricing(
            @Valid @ModelAttribute("pricingForm") PricingForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData data
    ) {

        if (validation.hasErrors())
            return "pricing";

        data.setPricingForm(form);
        return "redirect:/checkout";
    }

    // -------------------------------------------------------------
    // STEP 9 — CHECKOUT (GET + POST)
    // -------------------------------------------------------------
    @GetMapping("/checkout")
    public String showCheckout() {
        return "checkout";
    }

    @PostMapping("/checkout")
    public String finish() {
        return "redirect:/";
    }

    // -------------------------------------------------------------
    // RESET FLOW
    // -------------------------------------------------------------
    @PostMapping("/start-over")
    public String reset(SessionStatus status) {
        status.setComplete();
        return "redirect:/country";
    }
}
