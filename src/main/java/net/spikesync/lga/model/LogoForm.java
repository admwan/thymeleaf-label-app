package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LogoForm {

	@NotEmpty(message = "Logo type must be selected")
	private String logoType;
	
	@NotNull(message = "Please choose whether to include the Triman logo")
	private Boolean trimanIncluded;
	
    private String customText; // Optional, so no validation

	// Constructors
	public LogoForm() {
	}

	// Getters and Setters
	public String getLogoType() {
		return logoType;
	}

	public void setLogoType(String logoType) {
		this.logoType = logoType;
	}

	public String getCustomText() {
		return customText;
	}

	public void setCustomText(String customText) {
		this.customText = customText;
	}
	public Boolean getTrimanIncluded() {
	    return trimanIncluded;
	}

	public void setTrimanIncluded(Boolean trimanIncluded) {
	    this.trimanIncluded = trimanIncluded;
	}
}
