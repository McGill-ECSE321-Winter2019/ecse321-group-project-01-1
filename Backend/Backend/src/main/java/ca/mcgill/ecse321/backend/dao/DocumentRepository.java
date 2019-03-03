package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.Internship;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Document;

import java.util.List;

public interface DocumentRepository extends CrudRepository<Document, Integer>{

	Document findDocumentById(int id);
	List<Document> findDocumentByInternship(Internship internship);
}