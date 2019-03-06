package ca.mcgill.ecse321.backend.dto;

import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import java.util.Collections;
import java.util.List;

public class DocumentDto {

    private String id;
    private String fileName;
    private String path;
    private String fileType;
    private long size;
    private DocumentType documentType;

    public DocumentDto(String id, String fileName, String path, String fileType, long size,DocumentType type) {
        this.id = id;
        this.fileName = fileName;
        this.path = path;
        this.fileType = fileType;
        this.size = size;
        this.documentType = type;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String fileDownloadUri) {
        this.path = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
