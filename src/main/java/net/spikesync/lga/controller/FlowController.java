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

    // Start at country selection
    @GetMapping("/")
    public String showCountryForm(Model model, 
                                   @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("countryForm", sessionData.getCountryForm() != null ? sessionData.getCountryForm() : new CountryForm());
        model.addAttribute("countries", Country.values()); // 🔥 Add this line
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
    
    
    @GetMapping("/product")
    public String showProductForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("productForm", sessionData.getProductForm() != null ? sessionData.getProductForm() : new ProductForm());
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

    @GetMapping("/product-details")
    public String showProductDetailsForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("productDetailsForm", sessionData.getProductDetailsForm() != null ? sessionData.getProductDetailsForm() : new ProductDetailsForm());
        return "productDetails";
    }

    @PostMapping("/ingredients")
    public String handleProductDetailsForm(@Valid @ModelAttribute("productDetailsForm") ProductDetailsForm productDetailsForm,
                                           BindingResult bindingResult,
                                           @ModelAttribute("sessionData") LabelGenerationSessionData sessionData,
                                           Model model) {
        if (bindingResult.hasErrors()) {
            return "productDetails"; // stay here if errors
        }

        sessionData.setProductDetailsForm(productDetailsForm);
        return "redirect:/ingredients"; // ✅ move forward correctly
    }


    @GetMapping("/ingredients")
    public String showIngredientsForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("ingredientForm", sessionData.getIngredientForm() != null ? sessionData.getIngredientForm() : new IngredientForm());
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

    @GetMapping("/custom-size")
    public String showCustomSizeForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("sizeForm", sessionData.getSizeForm() != null ? sessionData.getSizeForm() : new SizeForm());
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

    @GetMapping("/logo-generation")
    public String showLogoGenerationForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("logoForm", sessionData.getLogoForm() != null ? sessionData.getLogoForm() : new LogoForm());
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
    @GetMapping("/file-upload")
    public String showFileUploadForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("fileUploadForm", sessionData.getFileUploadForm() != null ? sessionData.getFileUploadForm() : new FileUploadForm());
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

    @GetMapping("/pricing")
    public String showPricingForm(Model model, @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("pricingForm", sessionData.getPricingForm() != null ? sessionData.getPricingForm() : new PricingForm());
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

    @GetMapping("/checkout")
    public String showCheckout(Model model, 
                                @ModelAttribute("sessionData") LabelGenerationSessionData sessionData) {
        model.addAttribute("sessionData", sessionData);
        return "checkout"; // ❌ No sessionStatus.setComplete() here!
    }

    @PostMapping("/start-over")
    public String startOver(SessionStatus sessionStatus) {
        sessionStatus.setComplete(); // ✅ Only clear here
        return "redirect:/";
    }


}