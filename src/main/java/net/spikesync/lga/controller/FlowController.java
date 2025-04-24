// Placeholder for FlowController.java
package net.spikesync.lga.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FlowController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to the Label Generator");
        return "layout"; // assuming layout.html includes content dynamically
    }

    @GetMapping("/product")
    public String productForm(Model model) {
        // add form data model if needed
        return "product";
    }

    @GetMapping("/country")
    public String countryForm(Model model) {
        return "country";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        return "fileUpload";
    }
}
