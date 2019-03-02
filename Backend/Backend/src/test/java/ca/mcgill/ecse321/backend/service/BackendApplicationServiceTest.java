package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import java.util.*;

import javax.transaction.Transactional;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.dao.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationServiceTest {
	
	
	@Autowired
	private BackendApplicationService service;



	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private ApplicationFormRepository applicationFormRepository;
	@Autowired
	private ReminderRepository reminderRepository;


	@Before
	public void clearDatabase() {
		studentRepository.deleteAll();
		documentRepository.deleteAll();
		applicationFormRepository.deleteAll();		
		reminderRepository.deleteAll();
	}

	@Test
	public void testStudent() {
		//assert no student in repository
		assertEquals(0, service.getAllStudents().size());

		//create new student
		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		try {
			service.createStudent(id, fname, lname, email, pass);
		} catch (IllegalArgumentException e) {
			fail();
		}

		//assert only 1 student
		List<Student> allStudents = service.getAllStudents();
		assertEquals(1, allStudents.size());

		//read student
		Student test = service.readStudent(id);

		assertEquals(test.getFirstName(),fname);
		assertEquals(test.getLastName(),lname);
		assertEquals(test.getStudentID(),id);
		assertEquals(test.getEmail(),email);
		assertEquals(test.getPassword(),pass);

		//write to student
		test.setFirstName("Bob");
		test.setLastName("Thing");
		test.setStudentID("111111111");
		test.setEmail("bob.thing@mail.mcgill.ca");
		test.setPassword("654321");

		assertEquals(test.getFirstName(),"Bob");
		assertEquals(test.getLastName(),"Thing");
		assertEquals(test.getStudentID(),"111111111");
		assertEquals(test.getEmail(),"bob.thing@mail.mcgill.ca");
		assertEquals(test.getPassword(),"654321");
	}
	
	@Test
	public void testReminder() {
		//assert no student in repository
		assertEquals(0, service.getAllStudents().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		
		String message = "Reminder";
		
		Student teststudent = service.createStudent(id, fname, lname, email, pass);

		//create reminder

		assertEquals(0, service.getAllReminders().size());
		
		Reminder r = service.createReminder(teststudent, message);
	
		assertEquals(1, service.getAllReminders().size());

		//read reminder
				
		assertEquals(r.getMessage(),message);
		
		//write reminder
		r.setMessage("Alert");	
		assertEquals(r.getMessage(),"Alert");
	
	}


	public void testDocument(){
		assertEquals(0, service.getAllStudents().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		String jobid = "123456";

		String path = "C:";
		Student teststudent = service.createStudent(id, fname, lname, email, pass);
		Course testCourse = service.createCourse("Facc 300");
		Internship internship = service.createInternship(teststudent,testCourse);

		//create document
		assertEquals(0, service.getAllDocuments().size());
		ApplicationForm af = service.createApplicationForm(internship, jobid);

		assertEquals(1, service.getAllDocuments().size());
	}

	@Test
	public void testInternship(){
		assertEquals(0, service.getAllInternships().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		Student teststudent = service.createStudent(id, fname, lname, email, pass);
		Course testCourse = service.createCourse("FACC300");

	}

	public void testCourse(){

		assertEquals(0, service.getAllCourses().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		Student teststudent = service.createStudent(id, fname, lname, email, pass);
		service.createCourse("FACC300");

	}


	public void testApplicationForm() {
		assertEquals(0, service.getAllStudents().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		String jobid = "123456";
		Internship internship;

		Student teststudent = service.createStudent(id, fname, lname, email, pass);
		Course testCourse = service.createCourse("FACC300");
		internship = service.createInternship(teststudent,testCourse);
		ApplicationForm af = service.createApplicationForm(internship, jobid);

		//create application form
		assertEquals(0, service.getAllApplicationForms().size());

		assertEquals(1, service.getAllApplicationForms().size());

		//read application form

		assertEquals(af.getJobID(),jobid);

		//write application form
		af.setJobID("654321");
		assertEquals(af.getJobID(),"654321");

	}
	


}
