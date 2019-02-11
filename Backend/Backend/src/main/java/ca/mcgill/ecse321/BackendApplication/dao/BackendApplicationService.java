package ca.mcgill.ecse321.BackendApplication.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.BackendApplication.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.BackendApplication.dao.DocumentRepository;
import ca.mcgill.ecse321.BackendApplication.dao.ReminderRepository;
import ca.mcgill.ecse321.BackendApplication.dao.StudentRepository;

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
	DocumentRepository documentRepository;
	ApplicationFormRepository applicationFormRepository;
	ReminderRepository reminderRepository;
	
	
	//CRUD - Create, Read, Update, Delete
	
	//Student
	@Transactional
	public Student createStudent(String studentID, String firstName, 
								String lastName, String email, String password) {
		Student S = new Student();
		S.setStudentID(studentID);
		S.setFirstName(firstName);
		S.setLastName(lastName);
		S.setEmail(email);
		S.setPassword(password);
		studentRepository.save(S);
		return S;
	}
	
	@Transactional
	public Student readStudent(String studentID) {
		Student S = studentRepository.findStudentByStudentID(studentID);
		return S;
	}
	
	//Document
	@Transactional
	public Document createDocument(String path) {
		Document D = new Document();
		D.setPath(path);
		documentRepository.save(D);
		return D;
	}
	
	@Transactional
	public Document readDocument (int ID) {
		Document D = documentRepository.findDocumentById(ID);
		return D;
	}
	
	//ApplicationForm
	@Transactional
	public ApplicationForm createApplicationForm (int jobID) {
		ApplicationForm A = new ApplicationForm();
		A.setId(jobID);
		applicationFormRepository.save(A);
		return A;
	}
	
	@Transactional
	public ApplicationForm readApplicationForm (int ID) {
		ApplicationForm A = applicationFormRepository.findFormById(ID);
		return A;
	}
	
	//Reminder
	@Transactional
	public Reminder createReminder (String message) {
		Reminder R = new Reminder();
		R.setMessage(message);
		reminderRepository.save(R);
		return R;
	}
	
	@Transactional
	public Reminder readReminder (int ID) {
		Reminder R = reminderRepository.findReminderById(ID);
		return R;
	}
}
