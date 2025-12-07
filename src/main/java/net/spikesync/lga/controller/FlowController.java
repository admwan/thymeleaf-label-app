package net.spikesync.lga.controller;

import jakarta.validation.Valid;

import net.spikesync.lga.model.Country;
import net.spikesync.lga.model.CountryForm;
import net.spikesync.lga.model.ProductForm;
import net.spikesync.lga.model.ProductDetailsForm;
import net.spikesync.lga.model.IngredientForm;
import net.spikesync.lga.model.SizeForm;
import net.spikesync.lga.model.LogoForm;
import net.spikesync.lga.model.FileUploadForm;
import net.spikesync.lga.model.PricingForm;
import net.spikesync.lga.model.LabelGenerationSessionData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import org.springframework.web.multipart.MultipartFile;


@Controller
@SessionAttributes("sessionData")
public class FlowController {

    private static final Logger log = LoggerFactory.getLogger(FlowController.class);

    // -------------------------
    //  Session Attribute Setup
    // -------------------------
    @ModelAttribute("sessionData")
    public LabelGenerationSessionData initializeSession() {
        log.debug("Creating NEW sessionData");
        return new LabelGenerationSessionData();
    }

    // -------------------------
    //  Helper Methods
    // -------------------------

    private void logEnter(String pageName) {
        log.debug("=== Entering page: {} ===", pageName);
    }

    private boolean missing(Object obj) {
        return obj == null;
    }

    private boolean missingValue(String s) {
        return s == null || s.isBlank();
    }

    private boolean ensureStepCompleted(boolean condition, String redirectTarget) {
        return condition ? false : true;
    }

    // -------------------------------------------------------------
    // HOME
    // -------------------------------------------------------------
    @GetMapping("/")
    public String home() {
        return "home";
    }


    // --------------------------------------------------------
    //  STEP 1: COUNTRY
    // --------------------------------------------------------

    @GetMapping("/country")
    public String showCountryForm(@ModelAttribute("sessionData") LabelGenerationSessionData sessionData, Model model) {

        logEnter("country");

        if (missing(sessionData.getCountryForm())) {
            sessionData.setCountryForm(new CountryForm());
        }

        model.addAttribute("countryForm", sessionData.getCountryForm());
        model.addAttribute("countries", Country.values());
        return "country";
    }


    @PostMapping("/product")
    public String handleCountryForm(
            @Valid @ModelAttribute("countryForm") CountryForm countryForm,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("POST /product (country)");

        if (validation.hasErrors()) {
            model.addAttribute("countries", Country.values());
            return "country";
        }

        sessionData.setCountryForm(countryForm);
        return "redirect:/product";
    }


    // --------------------------------------------------------
    //  STEP 2: PRODUCT
    // --------------------------------------------------------

    @GetMapping("/product")
    public String showProductForm(@ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                  Model model) {

        logEnter("product");

        if (missing(sessionData.getCountryForm())) {
            return "redirect:/country";
        }

        if (missing(sessionData.getProductForm())) {
            sessionData.setProductForm(new ProductForm());
        }

        model.addAttribute("productForm", sessionData.getProductForm());
        return "product";
    }


    @PostMapping("/product-details")
    public String handleProductForm(
            @Valid @ModelAttribute("productForm") ProductForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /product-details");

        if (validation.hasErrors()) {
            return "product";
        }

        sessionData.setProductForm(form);
        return "redirect:/product-details";
    }

    // --------------------------------------------------------
    //  STEP 3: PRODUCT DETAILS
    // --------------------------------------------------------

    @GetMapping("/product-details")
    public String showProductDetails(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("product-details");

        if (missing(sessionData.getProductForm())) {
            return "redirect:/product";
        }

        if (missing(sessionData.getProductDetailsForm())) {
            sessionData.setProductDetailsForm(new ProductDetailsForm());
        }

        model.addAttribute("productDetailsForm", sessionData.getProductDetailsForm());
        return "productDetails";
    }


    @PostMapping("/ingredients")
    public String handleProductDetails(
            @Valid @ModelAttribute("productDetailsForm") ProductDetailsForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /ingredients");

        if (validation.hasErrors()) {
            return "productDetails";
        }

        sessionData.setProductDetailsForm(form);
        return "redirect:/ingredients";
    }

    // --------------------------------------------------------
    //  STEP 4: INGREDIENTS
    // --------------------------------------------------------

    @GetMapping("/ingredients")
    public String showIngredients(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("ingredients");

        if (missing(sessionData.getProductDetailsForm())) {
            return "redirect:/product-details";
        }

        if (missing(sessionData.getIngredientForm())) {
            sessionData.setIngredientForm(new IngredientForm());
        }

        model.addAttribute("ingredientForm", sessionData.getIngredientForm());
        return "ingredients";
    }


    @PostMapping("/custom-size")
    public String handleIngredients(
            @Valid @ModelAttribute("ingredientForm") IngredientForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /custom-size");

        if (validation.hasErrors()) {
            return "ingredients";
        }

        sessionData.setIngredientForm(form);
        return "redirect:/custom-size";
    }


    // --------------------------------------------------------
    //  STEP 5: SIZE
    // --------------------------------------------------------

    @GetMapping("/custom-size")
    public String showSize(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("custom-size");

        if (missing(sessionData.getIngredientForm())) {
            return "redirect:/ingredients";
        }

        if (missing(sessionData.getSizeForm())) {
            sessionData.setSizeForm(new SizeForm());
        }

        model.addAttribute("sizeForm", sessionData.getSizeForm());
        return "customSize";
    }


    @PostMapping("/logo-generation")
    public String handleSize(
            @Valid @ModelAttribute("sizeForm") SizeForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /logo-generation");

        if (validation.hasErrors()) {
            return "customSize";
        }

        sessionData.setSizeForm(form);
        return "redirect:/logo-generation";
    }


    // --------------------------------------------------------
    //  STEP 6: LOGO SELECTION
    // --------------------------------------------------------

    @GetMapping("/logo-generation")
    public String showLogo(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("logo-generation");

        boolean isFrance =
        	    sessionData.getCountryForm() != null &&
        	    "FRANCE".equalsIgnoreCase(sessionData.getCountryForm().getSelectedCountry());

        	model.addAttribute("showTrimanOption", isFrance);

        if (missing(sessionData.getSizeForm())) {
            return "redirect:/custom-size";
        }

        if (missing(sessionData.getLogoForm())) {
            sessionData.setLogoForm(new LogoForm());
        }

        model.addAttribute("logoForm", sessionData.getLogoForm());
        return "logoGeneration";
    }


    @PostMapping("/file-upload")
    public String handleLogo(
            @ModelAttribute("logoForm") LogoForm form,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /file-upload");

        sessionData.setLogoForm(form);

        return "redirect:/file-upload";
    }

    // --------------------------------------------------------
    //  STEP 7: FILE UPLOAD
    // --------------------------------------------------------

    @GetMapping("/file-upload")
    public String showFileUpload(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("file-upload");

        if (missing(sessionData.getLogoForm())) {
            return "redirect:/logo-generation";
        }

        if (missing(sessionData.getFileUploadForm())) {
            sessionData.setFileUploadForm(new FileUploadForm());
        }

        model.addAttribute("fileUploadForm", sessionData.getFileUploadForm());
        return "fileUpload";
    }


    @PostMapping("/pricing")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /pricing");

        if (!file.isEmpty()) {
            sessionData.getFileUploadForm().setFileName(file.getOriginalFilename());
            log.debug("Uploaded file: {}", file.getOriginalFilename());
        } else {
            log.warn("Upload attempted with empty file");
        }

        return "redirect:/pricing";
    }

    // --------------------------------------------------------
    //  STEP 8: PRICING
    // --------------------------------------------------------

    @GetMapping("/pricing")
    public String showPricing(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("pricing");

        if (missing(sessionData.getFileUploadForm()) ||
            missing(sessionData.getFileUploadForm().getFileName())) {
            return "redirect:/file-upload";
        }

        if (missing(sessionData.getPricingForm())) {
            sessionData.setPricingForm(new PricingForm());
        }

        model.addAttribute("pricingForm", sessionData.getPricingForm());
        return "pricing";
    }


    @PostMapping("/checkout")
    public String handlePricing(
            @Valid @ModelAttribute("pricingForm") PricingForm form,
            BindingResult validation,
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {

        logEnter("POST /checkout");

        if (validation.hasErrors()) {
            return "pricing";
        }

        sessionData.setPricingForm(form);
        return "redirect:/checkout";
    }

    // --------------------------------------------------------
    //  STEP 9: CHECKOUT
    // --------------------------------------------------------

    @GetMapping("/checkout")
    public String showCheckout(
            @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
            Model model) {

        logEnter("checkout");

        if (missing(sessionData.getPricingForm())) {
            return "redirect:/pricing";
        }

        return "checkout";
    }
}
