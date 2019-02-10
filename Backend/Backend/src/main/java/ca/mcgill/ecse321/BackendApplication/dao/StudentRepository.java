package ca.mcgill.ecse321.BackendApplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer>{

	Student findStudentByID(int studentID);

}