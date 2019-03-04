package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.Student;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Internship;

import java.util.Set;

public interface InternshipRepository extends CrudRepository<Internship, Integer>{

	Internship findInternshipById(int id);
	Internship findByIdAndStudentStudentID(int id, String studentID);
	Internship findByIdAndStudent(int id, Student student);
	Set<Internship> findAllInternshipByStudent(Student student);

}