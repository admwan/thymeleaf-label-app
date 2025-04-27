package net.spikesync.lga.model;

import jakarta.validation.constraints.NotNull;

public class PricingForm {
    @NotNull(message = "Price must be specified")
    private Double price = 0.0;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
