package ca.mcgill.ecse321.backend.dto;

import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import java.util.Collections;
import java.util.List;

public class DocumentDto {

    public DocumentDto(){

    }

    private String path;

    private String id;

    private String fileName;

    private String fileType;

    private byte[] data;

    public Internship getInternship() {
        return this.internship;
    }

    private DocumentType documentType;

    private Internship internship;

    public void setInternship(Internship internship) {
        this.internship = internship;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public byte[] getData(){
        return data;
    }

    public DocumentType getDocumentType() {
        return this.documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getId() {
        return id;
    }

}
