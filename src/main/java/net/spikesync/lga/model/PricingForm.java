package net.spikesync.lga.model;

import jakarta.validation.constraints.NotNull;

public class PricingForm {

    @NotNull(message = "Price must be specified")
    private Double price; // ✅ Wrapper Double, not primitive double

    // Getters and Setters
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
