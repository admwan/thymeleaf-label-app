package net.spikesync.lga.controller;

import net.spikesync.lga.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.validation.Valid;


@Controller
@SessionAttributes("sessionData")
public class FlowController {

    @ModelAttribute("sessionData")
    public LabelGenerationSessionData createSessionData() {
        return new LabelGenerationSessionData();
    }

    // ------------------------- Country -------------------------

    @GetMapping("/")
    public String showCountryForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getCountryForm() == null) {
            sessionData.setCountryForm(new CountryForm());
        }
        model.addAttribute("countryForm", sessionData.getCountryForm());
        model.addAttribute("countries", Country.values());
        return "country";
    }

    @PostMapping("/product")
    public String handleCountryForm(@Valid @ModelAttribute("countryForm") CountryForm countryForm,
                                    BindingResult bindingResult,
                                    @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("countries", Country.values());
            return "country";
        }
        sessionData.setCountryForm(countryForm);
        return "redirect:/product";
    }

    // ------------------------- Product -------------------------

    @GetMapping("/product")
    public String showProductForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getProductForm() == null) {
            sessionData.setProductForm(new ProductForm());
        }
        model.addAttribute("productForm", sessionData.getProductForm());
        return "product";
    }

    @PostMapping("/product-details")
    public String handleProductForm(@Valid @ModelAttribute("productForm") ProductForm productForm,
                                    BindingResult bindingResult,
                                    @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "product";
        }
        sessionData.setProductForm(productForm);
        return "redirect:/product-details";
    }

    // ------------------------- Product Details -------------------------

    @GetMapping("/product-details")
    public String showProductDetailsForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getProductDetailsForm() == null) {
            sessionData.setProductDetailsForm(new ProductDetailsForm());
        }
        model.addAttribute("productDetailsForm", sessionData.getProductDetailsForm());
        return "productDetails";
    }

    @PostMapping("/ingredients")
    public String handleProductDetailsForm(@Valid @ModelAttribute("productDetailsForm") ProductDetailsForm productDetailsForm,
                                           BindingResult bindingResult,
                                           @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                           Model model) {
        if (bindingResult.hasErrors()) {
            return "productDetails";
        }
        sessionData.setProductDetailsForm(productDetailsForm);
        return "redirect:/ingredients";
    }

    // ------------------------- Ingredients -------------------------

    @GetMapping("/ingredients")
    public String showIngredientsForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getIngredientForm() == null) {
            sessionData.setIngredientForm(new IngredientForm());
        }
        model.addAttribute("ingredientForm", sessionData.getIngredientForm());
        return "ingredients";
    }

    @PostMapping("/custom-size")
    public String handleIngredientsForm(@Valid @ModelAttribute("ingredientForm") IngredientForm ingredientForm,
                                        BindingResult bindingResult,
                                        @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                        Model model) {
        if (bindingResult.hasErrors()) {
            return "ingredients";
        }
        sessionData.setIngredientForm(ingredientForm);
        return "redirect:/custom-size";
    }

    // ------------------------- Custom Size -------------------------

    @GetMapping("/custom-size")
    public String showCustomSizeForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getSizeForm() == null) {
            sessionData.setSizeForm(new SizeForm());
        }
        model.addAttribute("sizeForm", sessionData.getSizeForm());
        return "customSize";
    }

    @PostMapping("/logo-generation")
    public String handleSizeForm(@Valid @ModelAttribute("sizeForm") SizeForm sizeForm,
                                 BindingResult bindingResult,
                                 @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "customSize";
        }
        sessionData.setSizeForm(sizeForm);
        return "redirect:/logo-generation";
    }

    // ------------------------- Logo Generation -------------------------

    @GetMapping("/logo-generation")
    public String showLogoGenerationForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getLogoForm() == null) {
            sessionData.setLogoForm(new LogoForm());
        }
        model.addAttribute("logoForm", sessionData.getLogoForm());
        return "logoGeneration";
    }

    @PostMapping("/file-upload")
    public String handleLogoForm(@Valid @ModelAttribute("logoForm") LogoForm logoForm,
                                 BindingResult bindingResult,
                                 @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "logoGeneration";
        }
        sessionData.setLogoForm(logoForm);
        return "redirect:/file-upload";
    }

    // ------------------------- File Upload -------------------------

    @GetMapping("/file-upload")
    public String showFileUploadForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getFileUploadForm() == null) {
            sessionData.setFileUploadForm(new FileUploadForm());
        }
        model.addAttribute("fileUploadForm", sessionData.getFileUploadForm());
        return "fileUpload";
    }

    @PostMapping("/pricing")
    public String handleFileUploadForm(@Valid @ModelAttribute("fileUploadForm") FileUploadForm fileUploadForm,
                                       BindingResult bindingResult,
                                       @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                       Model model) {
        if (bindingResult.hasErrors()) {
            return "fileUpload";
        }
        sessionData.setFileUploadForm(fileUploadForm);
        return "redirect:/pricing";
    }

    // ------------------------- Pricing -------------------------

    @GetMapping("/pricing")
    public String showPricingForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        if (sessionData.getPricingForm() == null) {
            sessionData.setPricingForm(new PricingForm());
        }
        model.addAttribute("pricingForm", sessionData.getPricingForm());
        return "pricing";
    }

    @PostMapping("/checkout")
    public String handlePricingForm(@Valid @ModelAttribute("pricingForm") PricingForm pricingForm,
                                    BindingResult bindingResult,
                                    @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            return "pricing";
        }
        sessionData.setPricingForm(pricingForm);
        return "redirect:/checkout";
    }

    // ------------------------- Checkout -------------------------

    @GetMapping("/checkout")
    public String showCheckout(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("sessionData", sessionData);
        return "checkout";
    }

    // ------------------------- Start Over -------------------------

    @PostMapping("/start-over")
    public String startOver(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/";
    }
}