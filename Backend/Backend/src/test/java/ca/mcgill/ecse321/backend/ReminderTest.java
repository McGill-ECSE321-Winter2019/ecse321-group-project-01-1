package ca.mcgill.ecse321.backend;

import java.sql.Time;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.ReminderRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;
//import 
//ca.mcgill.ecse321.backend.BackendApplicationController;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReminderTest {
	
	@InjectMocks
	private Reminder reminder; //entity to test on
	@Mock
	private Student dummyStudent = mock(Student.class); 

	private Time dummyCreateTime = new Time(20);
	private Time dummyReadTime = new Time (40);
	
	private static final int REMINDER_ID = 12345;
	private static final String MESSAGE = "I love cats";

	@Before
	public void setReminder() {
		reminder.setCreateDateTime(dummyCreateTime);
		reminder.setReadDateTime(dummyReadTime);
		reminder.setId(REMINDER_ID);
		reminder.setMessage(MESSAGE);
		reminder.setStudent(dummyStudent);
	}
		
	@Test
	public void getId() {
		assertEquals(REMINDER_ID,reminder.getId());
	}
	
	@Test
	public void getMessage() {
		assertEquals(MESSAGE,reminder.getMessage());
	}
	
	@Test
	public void getStudent() {
		assertEquals(dummyStudent,reminder.getStudent());
	}
	
	@Test
	public void getCreateDate() {
		assertEquals(dummyCreateTime,reminder.getCreateDateTime());
	}
	
	@Test
	public void getReadDate() {
		assertEquals(dummyReadTime,reminder.getReadDateTime());
	}
	
	
}
