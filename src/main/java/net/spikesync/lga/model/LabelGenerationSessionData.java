package net.spikesync.lga.model;

import java.io.Serializable;

public class LabelGenerationSessionData implements Serializable {

    private CountryForm countryForm;
    private ProductForm productForm;
    private ProductDetailsForm productDetailsForm;
    private IngredientForm ingredientForm;
    private SizeForm sizeForm;
    private LogoForm logoForm;
    private FileUploadForm fileUploadForm;
    private PricingForm pricingForm;

    public LabelGenerationSessionData() {
        this.countryForm = new CountryForm();
        this.productForm = new ProductForm();
        this.productDetailsForm = new ProductDetailsForm();
        this.ingredientForm = new IngredientForm();
        this.sizeForm = new SizeForm();
        this.logoForm = new LogoForm();
        this.fileUploadForm = new FileUploadForm();
        this.pricingForm = new PricingForm();
    }

    // getters and setters for all fields
    public CountryForm getCountryForm() { return countryForm; }
    public void setCountryForm(CountryForm countryForm) { this.countryForm = countryForm; }

    public ProductForm getProductForm() { return productForm; }
    public void setProductForm(ProductForm productForm) { this.productForm = productForm; }

    public ProductDetailsForm getProductDetailsForm() { return productDetailsForm; }
    public void setProductDetailsForm(ProductDetailsForm productDetailsForm) { this.productDetailsForm = productDetailsForm; }

    public IngredientForm getIngredientForm() { return ingredientForm; }
    public void setIngredientForm(IngredientForm ingredientForm) { this.ingredientForm = ingredientForm; }

    public SizeForm getSizeForm() { return sizeForm; }
    public void setSizeForm(SizeForm sizeForm) { this.sizeForm = sizeForm; }

    public LogoForm getLogoForm() { return logoForm; }
    public void setLogoForm(LogoForm logoForm) { this.logoForm = logoForm; }

    public FileUploadForm getFileUploadForm() { return fileUploadForm; }
    public void setFileUploadForm(FileUploadForm fileUploadForm) { this.fileUploadForm = fileUploadForm; }

    public PricingForm getPricingForm() { return pricingForm; }
    public void setPricingForm(PricingForm pricingForm) { this.pricingForm = pricingForm; }
}
