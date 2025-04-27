package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class IngredientForm {
    
	@NotEmpty(message = "Please list at least one ingredient")
    private String ingredients;

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
}
