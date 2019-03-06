package ca.mcgill.ecse321.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Document;

public interface DocumentRepository extends CrudRepository<Document, Integer>{

	Document findDocumentById(int id);

}