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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationServicesTest {
	
	
	@Autowired
	private BackendApplicationService service;

	@Autowired
	private StudentRepository studentRepository;
	

	@After
	public void clearDatabase() {
		
		studentRepository.deleteAll();
		
	}
	
	@Test
	public void testCreateStudent() {
		assertEquals(0, service.getAllStudents().size());

		String name = "Oscar";

		try {
			service.createStudent(name, name, name, name, name);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Student> allPersons = service.getAllStudents();

		assertEquals(1, allPersons.size());
		assertEquals(name, allPersons.get(0).getFirstName());
	}
	


}
