package net.spikesync.lga.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleError(Exception ex, Model model) {
    	if (ex instanceof NoResourceFoundException &&
    		    ((NoResourceFoundException) ex).getResourcePath().equals("favicon.ico")) {
    		    return null; // let Spring return 404 silently
    		}

        model.addAttribute("message", ex.getMessage());
        return "error"; // Renders error.html
    }
}