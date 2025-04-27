package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class ProductDetailsForm {

	@NotEmpty(message = "Please provide a name for your product")
	private String productName;
    
    @NotEmpty(message = "Product description is required")
    private String productDescription;

    @NotEmpty(message = "Product weight is required")
    private String productWeight;

    @NotEmpty(message = "Packaging type is required")
    private String packagingType;

    public ProductDetailsForm() {
    }

    // Getters and Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

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
