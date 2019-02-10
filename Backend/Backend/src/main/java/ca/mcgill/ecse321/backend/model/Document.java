package ca.mcgill.ecse321.backend.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Document{
	
	private String path;
	
private Set<ApplicationForm> applicationForm;

@OneToMany(mappedBy="document")
public Set<ApplicationForm> getApplicationForm() {
   return this.applicationForm;
}

public void setApplicationForm(Set<ApplicationForm> applicationForms) {
   this.applicationForm = applicationForms;
}

private DocumentType documentType;

@OneToOne(optional=false)
public DocumentType getDocumentType() {
   return this.documentType;
}

public void setDocumentType(DocumentType documentType) {
   this.documentType = documentType;
}

}
