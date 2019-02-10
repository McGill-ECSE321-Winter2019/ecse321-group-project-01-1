package ca.mcgill.ecse321.BackendApplication.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

@Repository
public class BackendApplicationRepository {

	@Autowired
	EntityManager entityManager;
	
	
	//CRUD - Create, Read, Update, Delete
	
	//Student
	@Transactional
	public Student createStudent(int studentID, String firstName, 
								String lastName, String email, String password) {
		Student S = new Student();
		entityManager.persist(S);
		return new Student();
	}
	
	@Transactional
	public Student readStudent(int studentID) {
		Student S = entityManager.find(Student.class,studentID);
		return S;
	}
	

	
}
