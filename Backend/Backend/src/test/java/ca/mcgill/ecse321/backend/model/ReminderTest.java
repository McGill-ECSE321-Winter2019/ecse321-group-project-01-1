package ca.mcgill.ecse321.backend.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReminderTest {
	
	@InjectMocks
	private Reminder reminder; //entity to test on
	@Mock
	private Student dummyStudent = mock(Student.class); 

	private Date dummyCreateTime = new Date(20);
	private Date dummyReadTime = new Date(40);
	
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
