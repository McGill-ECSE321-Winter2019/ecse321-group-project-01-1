package ca.mcgill.ecse321.BackendApplication.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

@Service
public class BackendApplicationService {
	
	@Autowired
	StudentRepository studentRepository;
	
	
	//CRUD - Create, Read, Update, Delete
	
	//Student
	@Transactional
	public Student createStudent(int studentID, String firstName, 
								String lastName, String email, String password) {
		Student S = new Student();
		studentRepository.save(S);
		return new Student();
	}
	
	@Transactional
	public Student readStudent(int studentID) {
		Student S = studentRepository.findStudentByID(studentID);
		return S;
	}
	
	
}
