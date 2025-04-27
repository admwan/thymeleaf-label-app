package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class CountryForm {

    @NotEmpty(message = "Please select a country")
    private String selectedCountry;

    public CountryForm() {}

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }
}
