package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

<<<<<<< HEAD
import java.util.*;

import javax.transaction.Transactional;

import org.junit.*;
=======
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
>>>>>>> master
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

<<<<<<< HEAD
=======
	
	
>>>>>>> master
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private ApplicationFormRepository applicationFormRepository;
	@Autowired
	private ReminderRepository reminderRepository;
	

<<<<<<< HEAD
	@Before
	public void clearDatabase() {
		studentRepository.deleteAll();
		documentRepository.deleteAll();
		applicationFormRepository.deleteAll();		
		reminderRepository.deleteAll();
=======
	@After
	public void clearDatabase() {
		studentRepository.deleteAll();

		
>>>>>>> master
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
			
<<<<<<< HEAD
	}
	
	@Test
	@Transactional
=======
			
			
		
	}
	
	@Test
>>>>>>> master
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
		assertEquals(0, service.getAllReminders().size());
		
		Reminder r = service.createReminder(teststudent, message);
	
		assertEquals(1, service.getAllReminders().size());

		//read reminder
				
		assertEquals(r.getMessage(),message);
		
		//write reminder
		r.setMessage("Alert");	
		assertEquals(r.getMessage(),"Alert");
	
	}

//	@Test
//	public void testDocument() {
//		assertEquals(0, service.getAllStudents().size());
//
//		String id = "000000000";
//		String fname = "John";
//		String lname = "Doe";
//		String email = "john.doe@mail.mcgill.ca";
//		String pass = "123456";
//
//		String jobid = "123456";	
//
//		String path = "C:";	
//		Student teststudent = service.createStudent(id, fname, lname, email, pass);
//		
//		//create document
//		assertEquals(0, service.getAllDocuments().size());
//		ApplicationForm af = service.createApplicationForm(teststudent, jobid);
//		Document d = service.createDocument(af, path);
//	
//		assertEquals(1, service.getAllDocuments().size());
//		
//		//read document
//				
//		assertEquals(d.getPath(),path);
//		
//		//write document
//		d.setPath("D:");	
//		assertEquals(d.getPath(),"D:");
//
//	}
//	
//	@Test
//	public void testApplicationForm() {
//		assertEquals(0, service.getAllStudents().size());
//
//		String id = "000000000";
//		String fname = "John";
//		String lname = "Doe";
//		String email = "john.doe@mail.mcgill.ca";
//		String pass = "123456";
//
//		
//		String jobid = "123456";	
//		Student teststudent = service.createStudent(id, fname, lname, email, pass);
//		
//		//create application form
//		assertEquals(0, service.getAllApplicationForms().size());
//		
//		ApplicationForm af = service.createApplicationForm(teststudent, jobid);
//		
//		assertEquals(1, service.getAllApplicationForms().size());
//		
//		//read application form
//				
//		assertEquals(af.getJobID(),jobid);
//		
//		//write application form
//		af.setJobID("654321");	
//		assertEquals(af.getJobID(),"654321");
//
//	}

	


}
