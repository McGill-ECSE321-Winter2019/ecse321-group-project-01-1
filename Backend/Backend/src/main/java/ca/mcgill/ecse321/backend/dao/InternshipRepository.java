package ca.mcgill.ecse321.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Internship;

public interface InternshipRepository extends CrudRepository<Internship, Integer>{

	Internship findInternshipById(int id);

}