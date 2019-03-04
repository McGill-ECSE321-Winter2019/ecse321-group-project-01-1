package ca.mcgill.ecse321.backend.model;
import javax.persistence.ManyToOne;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Document{
	public Document(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
	public Document(){

	}

	private String path;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	private byte[] data;

	public Internship getInternship() {
		return this.internship;
	}

	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	@ManyToOne(optional=false)
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
