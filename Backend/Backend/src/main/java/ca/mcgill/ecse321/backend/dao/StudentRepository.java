package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String>{

	Student findStudentByStudentID(String studentID);
	Student findStudentByEmail(String email);

}