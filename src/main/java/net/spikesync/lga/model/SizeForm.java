package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class SizeForm {
    @NotEmpty(message = "Width is required")
    private String width;

    @NotEmpty(message = "Height is required")
    private String height;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
