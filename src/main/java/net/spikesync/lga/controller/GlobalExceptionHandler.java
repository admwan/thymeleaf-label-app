package net.spikesync.lga.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error"; // Renders error.html
    }
}