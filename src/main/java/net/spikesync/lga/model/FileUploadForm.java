package net.spikesync.lga.model;

import jakarta.validation.constraints.NotEmpty;

public class FileUploadForm {
    
	@NotEmpty(message = "Please specify the file name")
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
