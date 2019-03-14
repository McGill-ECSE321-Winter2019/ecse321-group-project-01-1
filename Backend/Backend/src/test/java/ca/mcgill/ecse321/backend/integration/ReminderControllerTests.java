package ca.mcgill.ecse321.backend.integration;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.ReminderService;
import ca.mcgill.ecse321.backend.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ReminderControllerTests {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;
	private Student mockStudent;
	private Reminder mockReminder;

	@Autowired
	private StudentService studentService;

	@Autowired
	private ReminderService reminderService;

	@Autowired
	private StudentRepository studentRepository;

	public void clearDatabase() {
		// this should be enough because of the composition
		studentRepository.deleteAll();
	}

	@Before
	public void setup() throws Exception {
		clearDatabase();

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();

		StudentDto studentDto = new StudentDto();
		studentDto.setStudentID("260123456");
		studentDto.setEmail("first.last@mail.mcgill.ca");
		studentDto.setPassword("123456");
		studentDto.setFirstName("First");
		studentDto.setLastName("Last");
		mockStudent = studentService.create(studentDto);

	    ReminderDto reminderDto = new ReminderDto("MESSAGE");
	    mockReminder = reminderService.create(reminderDto, mockStudent);
	    
	}

	@Test
	public void testCreateReminder() throws Exception {
		this.mockMvc.perform(
				post("/external/students/{student_id}/reminders", mockStudent.getStudentID()).param("message", "ERROR"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void testFetchReminders() throws Exception {
		this.mockMvc.perform(get("/external/students/{student_id}/reminders", mockStudent.getStudentID()))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].message", is(mockReminder.getMessage())));
	}
	
	@Test
	@WithAnonymousUser
	public void testReadReminderUnauthorized() throws Exception {
		this.mockMvc.perform(get("/api/reminders"))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testReadReminders() throws Exception {
		this.mockMvc.perform(get("/api/reminders"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].message", is(mockReminder.getMessage())));
	}
	
	@Test
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testReadReminder() throws Exception {
		this.mockMvc.perform(get("/api/reminders/{reminder_id}", mockReminder.getId()))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.message", is(mockReminder.getMessage())));
	}
}
