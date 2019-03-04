package ca.mcgill.ecse321.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {
	
	@InjectMocks
	private Student student; //entity to test on
	@Mock
	private Reminder reminder = mock(Reminder.class);
	@Mock
	private Internship internship = mock(Internship.class);
	
	private Set<Reminder> reminderSet = new HashSet<Reminder>();
	private Set<Internship> internshipSet = new HashSet<Internship>();
	
	private static final String STUDENT_ID = "12345";
	private static final String FIRST_NAME = "Bob";
	private static final String LAST_NAME = "Rob";
	private static final String EMAIL = "@mail.mcgill.ca";
	private static final String PASSWORD = "potato";
	private static final String MESSAGE = "I love cats";

	@Before
	public void setupSet() {
		reminderSet.add(reminder);
		internshipSet.add(internship);
	}
	
	@Before
	public void setStudent() {
		student.setStudentID(STUDENT_ID);
		student.setFirstName(FIRST_NAME);
		student.setLastName(LAST_NAME);
		student.setEmail(EMAIL);
		student.setPassword(PASSWORD);
		student.setReminder(reminderSet);
		student.setInternship(internshipSet);
	}
		
	@Test
	public void getId() {
		assertEquals(STUDENT_ID,student.getStudentID());
	}
	
	@Test
	public void getFirstName() {
		assertEquals(FIRST_NAME,student.getFirstName());
	}
	
	
	@Test
	public void getLastName() {
		assertEquals(LAST_NAME,student.getLastName());
	}
	
	@Test
	public void getEmail() {
		assertEquals(EMAIL,student.getEmail());
	}
	
	@Test
	public void getPassword() {
		assertEquals(PASSWORD,student.getPassword());
	}
	
	@Test
	public void getReminders() {
		assertEquals(reminderSet,student.getReminder());
	}
	
	@Test
	public void getInternships() {
		assertEquals(internshipSet,student.getInternship());
	}
	
	
}
