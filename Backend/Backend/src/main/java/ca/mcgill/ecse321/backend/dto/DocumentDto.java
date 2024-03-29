package ca.mcgill.ecse321.backend.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import ca.mcgill.ecse321.backend.model.DocumentType;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DocumentDto {

    private String id;
    private String fileName;
    private String path;
    private String fileType;
    private long size;
    private DocumentType documentType;
	private Date submissionDateTime;


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

	public Date getSubmissionDateTime() {
		return submissionDateTime;
	}

	public void setSubmissionDateTime(Date submissionDateTime) {
		this.submissionDateTime = submissionDateTime;
	}
}
