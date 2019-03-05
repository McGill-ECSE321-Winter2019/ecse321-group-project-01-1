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
import ca.mcgill.ecse321.backend.dao.*;
import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationServiceTest {

	
	@Autowired
	private StudentService studentService;

	@Autowired
	private ReminderService reminderService;
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private DocumentRepository documentRepository;
	@Autowired
	private ApplicationFormRepository applicationFormRepository;
	@Autowired
	private ReminderRepository reminderRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private InternshipRepository internshipRepository;

	@Before
	public void clearDatabase() {
		studentRepository.deleteAll();
		documentRepository.deleteAll();
		applicationFormRepository.deleteAll();
		reminderRepository.deleteAll();
		courseRepository.deleteAll();
		internshipRepository.deleteAll();
	}

	@Test
	@Transactional
	public void testReminder() throws Exception {
		//assert no student in repository
		assertEquals(0, studentService.getAll().size());

		String id = "000000000";
		String fname = "John";
		String lname = "Doe";
		String email = "john.doe@mail.mcgill.ca";
		String pass = "123456";

		String message = "Reminder";

		Student teststudent = studentService.create(new StudentDto(id, fname, lname, email, pass));

		//create reminder

		assertEquals(0, reminderService.getAll().size());

		Reminder r = reminderService.create(new ReminderDto(message), teststudent);

		assertEquals(1, reminderService.getAll().size());

		//read reminder

		assertEquals(r.getMessage(), message);

		//write reminder
		r.setMessage("Alert");
		assertEquals(r.getMessage(), "Alert");

	}
}
