package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Document;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, String>{

	Document findDocumentById(String id);
	List<Document> findDocumentByInternship(Internship internship);
	Document findDocumentByInternshipAndDocumentType(Internship internship, DocumentType type);
//	Boolean findDocumentByInternshipAndDocumentTypeExists(Internship internship, DocumentType type);
}