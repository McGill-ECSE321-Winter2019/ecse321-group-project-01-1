package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
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
	

	@After
	public void clearDatabase() {
		
		reminderRepository.deleteAll();
		applicationFormRepository.deleteAll();
		documentRepository.deleteAll();
		studentRepository.deleteAll();

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
		
		/*
		Reminder r = new Reminder();
		
		r.setMessage(message);
		
		List<Reminder> allReminder = service.getAllReminders();
		assertEquals(1, allReminder.size());
		
		*/
		
		//create reminder
		
		List<Reminder> allReminder = service.getAllReminders();
		assertEquals(0, allReminder.size());
		
		Reminder r = service.createReminder(teststudent, message);
	
		assertEquals(1, allReminder.size());

		//read reminder
				
		assertEquals(r.getMessage(),message);
		
		//write reminder
		r.setMessage("Alert");	
		assertEquals(r.getMessage(),"Alert");
	
	}
	
	@Test
	public void testDocument() {
		assertEquals(0, service.getAllStudents().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		
		String path = "C:";	
		Student teststudent = service.createStudent(id, fname, lname, email, pass);
		
		//create document
		List<Document> allDocuments = service.getAllDocuments();
		assertEquals(0, allDocuments.size());
		
		Document d = service.createDocument(teststudent, path);
	
		assertEquals(1, allDocuments.size());
		
		//read document
				
		assertEquals(d.getPath(),path);
		
		//write document
		d.setPath("D:");	
		assertEquals(d.getPath(),"D:");

	}
	
	@Test
	public void testApplicationForm() {
		assertEquals(0, service.getAllStudents().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		
		String jobid = "123456";	
		Student teststudent = service.createStudent(id, fname, lname, email, pass);
		
		//create application form
		List<Document> allApplicationForm = service.getAllDocuments();
		assertEquals(0, allApplicationForm.size());
		
		ApplicationForm af = service.createApplicationForm(teststudent, jobid);
	
		assertEquals(1, allApplicationForm.size());
		
		//read application form
				
		assertEquals(af.getJobID(),jobid);
		
		//write application form
		af.setJobID("654321");	
		assertEquals(af.getJobID(),"654321");

	}
	


}
