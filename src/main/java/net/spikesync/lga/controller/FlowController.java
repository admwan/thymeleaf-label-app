package net.spikesync.lga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import net.spikesync.lga.model.*;

@Controller
public class FlowController {

    @GetMapping("/")
    public String start(Model model) {
        model.addAttribute("productForm", new ProductForm());
        return "product";
    }

    @PostMapping("/product-details")
    public String productDetails(@ModelAttribute ProductForm productForm, Model model) {
        model.addAttribute("productDetailsForm", productForm);
        return "productDetails";
    }

    @PostMapping("/ingredients")
    public String ingredients(@ModelAttribute ProductForm productForm, Model model) {
        model.addAttribute("ingredientForm", new IngredientForm());
        return "ingrediants";
    }

    @PostMapping("/country")
    public String country(@ModelAttribute IngredientForm ingredientForm, Model model) {
        model.addAttribute("countryForm", new CountryForm());
        return "country";
    }

    @PostMapping("/custom-size")
    public String customSize(@ModelAttribute CountryForm countryForm, Model model) {
        model.addAttribute("sizeForm", new SizeForm());
        return "customSize";
    }

    @PostMapping("/logo-generation")
    public String logoGeneration(@ModelAttribute SizeForm sizeForm, Model model) {
        model.addAttribute("logoForm", new LogoForm());
        return "logoGeneration";
    }

    @PostMapping("/file-upload")
    public String fileUpload(@ModelAttribute LogoForm logoForm, Model model) {
        model.addAttribute("fileUploadForm", new FileUploadForm());
        return "fileUpload";
    }

    @PostMapping("/pricing")
    public String pricing(@ModelAttribute FileUploadForm fileUploadForm, Model model) {
        model.addAttribute("pricingForm", new PricingForm());
        return "pricing";
    }

    @PostMapping("/checkout")
    public String checkout(@ModelAttribute PricingForm pricingForm, Model model) {
        return "checkout";
    }

    @GetMapping("/info")
    public String infoPage() {
        return "infoPage";
    }
}
