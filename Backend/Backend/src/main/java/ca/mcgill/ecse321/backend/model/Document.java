package ca.mcgill.ecse321.backend.model;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Document{

	@Id
	@GeneratedValue
	private int id;

	private String path;

	@OneToMany
	private Set<ApplicationForm> applicationForm;

	public Set<ApplicationForm> getApplicationForm() {
		return this.applicationForm;
	}

	public void setApplicationForm(Set<ApplicationForm> applicationForms) {
		this.applicationForm = applicationForms;
	}

	@Enumerated(EnumType.STRING)
	private DocumentType documentType;

	public DocumentType getDocumentType() {
		return this.documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

}
