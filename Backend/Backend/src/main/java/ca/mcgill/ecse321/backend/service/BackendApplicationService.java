package ca.mcgill.ecse321.backend.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.ReminderRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

@Service
public class BackendApplicationService {
	
	@Autowired
	StudentRepository studentRepository;
	@Autowired
	DocumentRepository documentRepository;
	@Autowired
	ApplicationFormRepository applicationFormRepository;
	@Autowired
	ReminderRepository reminderRepository;
	@Autowired
	CourseRepository courseRepository;
	@Autowired
	InternshipRepository internshipRepository;
	
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
	
	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}
	
	//Document
	@Transactional
	public Document createDocument(ApplicationForm AF, String path) {
		Document D = new Document();
		D.setPath(path);
		D.setApplicationForm(AF);
		documentRepository.save(D);
		
		return D;
	}
	
	@Transactional
	public Document readDocument (int ID) {
		Document D = documentRepository.findDocumentById(ID);
		return D;
	}
	
	@Transactional
	public List<Document> getAllDocuments() {
		return toList(documentRepository.findAll());
	}

	//ApplicationForm
	@Transactional
	public ApplicationForm createApplicationForm (Student S, String jobID) {
		ApplicationForm A = new ApplicationForm();

		A.setJobID(jobID);
		A.setStudent(S);

		applicationFormRepository.save(A);
		
		return A;
	}
	
	@Transactional
	public ApplicationForm readApplicationForm (int ID) {
		ApplicationForm A = applicationFormRepository.findFormById(ID);
		return A;
	}
	
	@Transactional
	public List<ApplicationForm> getAllApplicationForms() {
		return toList(applicationFormRepository.findAll());
	}
	
	//Reminder
	@Transactional
	public Reminder createReminder (Student S, String message) {
		Reminder R = new Reminder();
		R.setMessage(message);
		reminderRepository.save(R);

		Set<Reminder> SR = S.getReminder();
		if (SR == null) {
			SR = new HashSet<Reminder>();
		}
		SR.add(R);
		S.setReminder(SR);
		studentRepository.save(S);
		
		return R;
	}
	
	@Transactional
	public Reminder readReminder (int ID) {
		Reminder R = reminderRepository.findReminderById(ID);
		return R;
	}
	
	@Transactional
	public List<Reminder> getAllReminders() {
		return toList(reminderRepository.findAll());
	}
	
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	// Course

	@Transactional
	public Course createCourse (String courseID) {
		Course course = new Course();
		course.setCourseID(courseID);
		course = courseRepository.save(course);
		return course;
	}

	@Transactional
	public Course readCourse(int id) {
		return courseRepository.findCourseById(id);
	}

	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}

}

