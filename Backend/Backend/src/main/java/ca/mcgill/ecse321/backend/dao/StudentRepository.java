package ca.mcgill.ecse321.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Student;

public interface StudentRepository extends CrudRepository<Student, String>{

	Student findStudentByStudentID(String studentID);
	Student findStudentByEmail(String email);

}