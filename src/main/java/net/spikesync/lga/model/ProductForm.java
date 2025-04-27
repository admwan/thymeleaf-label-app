package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class ProductForm {
  
	@NotEmpty(message = "Product name is required")
    private String productName;

    @NotEmpty(message = "Product category is required")
    private String productCategory;

    // Getters and setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }
}
