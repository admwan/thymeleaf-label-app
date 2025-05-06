package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class ProductDetailsForm {

    @NotEmpty(message = "Description is required")
    private String productDescription;

    @NotEmpty(message = "Weight is required")
    private String productWeight;

    @NotEmpty(message = "Packaging type is required")
    private String packagingType;

    // Getters and Setters

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductWeight() {
        return productWeight;
    }

    public void setProductWeight(String productWeight) {
        this.productWeight = productWeight;
    }

    public String getPackagingType() {
        return packagingType;
    }

    public void setPackagingType(String packagingType) {
        this.packagingType = packagingType;
    }
}
