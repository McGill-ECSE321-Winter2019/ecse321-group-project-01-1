package ca.mcgill.ecse321.backend.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.ApplicationFormService;
import ca.mcgill.ecse321.backend.service.CourseService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.ReminderService;
import ca.mcgill.ecse321.backend.service.StudentService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.sql.Date;
import java.util.Set;

 
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationFormControllerTests {
	
	private MockMvc mockMvc;
	
	private Student mockStudent;
	
	private Course mockCourse;
	
	private Internship mockInternship;
		
	@Autowired
	private ApplicationFormRepository appformRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private InternshipService internshipService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private ApplicationFormService applicationFormService;
	
	@Autowired
	private StudentService studentService;

	private ApplicationForm mockApplicationForm;

	private Student mockStudent2;
	
	@Before
	public void init() throws Exception {
		clearDatabase();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();

		mockCourse = courseService.create(new CourseDto("FACC 200"));
		
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentID("260123456");
		studentDto.setEmail("first.last@mail.mcgill.ca");
		studentDto.setPassword("123456");
		studentDto.setFirstName("First");
		studentDto.setLastName("Last");
		mockStudent = studentService.create(studentDto);
		
		studentDto = new StudentDto();
		studentDto.setStudentID("260123457");
		studentDto.setEmail("first.last1@mail.mcgill.ca");
		studentDto.setPassword("123456");
		studentDto.setFirstName("First");
		studentDto.setLastName("Last");
		mockStudent2 = studentService.create(studentDto);
		
		InternshipDto internshipDto = new InternshipDto();
		internshipDto.setAcademicSemester(AcademicSemester.SUMMER);
		mockInternship = internshipService.createInternship(internshipDto, mockStudent, mockCourse);
		
	
		Date startDate = Date.valueOf("2019-01-11");
		Date endDate = Date.valueOf("2019-01-22");
		
		ApplicationFormDto applicationFormDto = new ApplicationFormDto("1111111", "Description1", "Employer1", "Location1", startDate, endDate, true);
		mockApplicationForm = applicationFormService.createApplicationForm(applicationFormDto, mockInternship);
	}
	
	public void clearDatabase() {
		// this should be enough because of the composition
		studentRepository.deleteAll();
		courseRepository.deleteAll();
	}
	
	@Test
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testCreateApplicationForm() throws Exception {
		this.mockMvc.perform(post("/api/internships/{internship_id}/application_form", mockInternship.getId())
				.param("job_id", "123")
				.param("job_description", "Description")
				.param("employer", "Employer1")
				.param("location", "Joblocation")
				.param("start_date", (Date.valueOf("2019-01-11")).toString())
				.param("end_date", (Date.valueOf("2019-01-22")).toString())
				.param("work_permit", Boolean.toString(true))
				)
		.andExpect(status().isOk());
	}
	
	@Test 
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void getApplicationTest() throws Exception{
		mockMvc.perform(get("/api/internships/{internship_id}/application_form", mockInternship.getId()))
		 .andDo(print())
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.jobDescription", is(mockApplicationForm.getJobDescription())))
		 .andExpect(jsonPath("$.jobID", is(mockApplicationForm.getJobID())))
		 .andExpect(jsonPath("$.employer", is(mockApplicationForm.getEmployer())))
		 .andExpect(jsonPath("$.location", is(mockApplicationForm.getLocation())))
		 .andExpect(jsonPath("$.startDate", is(mockApplicationForm.getStartDate().toString())))
		 .andExpect(jsonPath("$.endDate", is(mockApplicationForm.getEndDate().toString())))
		 .andExpect(jsonPath("$.workPermit", is(true)));
	}
	
	@Test
	@WithAnonymousUser	
	public void testCreateApplicationFormUnauthorized() throws Exception {
		this.mockMvc.perform(post("/api/internships/{internship_id}/application_form", mockInternship.getId())
				.param("job_id", "123")
				.param("job_description", "Description")
				.param("employer", "Employer1")
				.param("location", "Joblocation")
				.param("start_date", (Date.valueOf("2019-01-11")).toString())
				.param("end_date", (Date.valueOf("2019-01-22")).toString())
				.param("work_permit", Boolean.toString(true))
				)
		.andExpect(status().isUnauthorized());
	}
	
	@Test 
	@WithAnonymousUser
	public void testFetchApplicationFormUnauthorized() throws Exception{
		
		mockMvc.perform(get("/api/internships/{internship_id}/application_form", mockInternship.getId()))
		 .andDo(print())
		 .andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser(username = "first.last1@mail.mcgill.ca")
	public void testCreateApplicationFormForbidden() throws Exception {
		this.mockMvc.perform(post("/api/internships/{internship_id}/application_form", mockInternship.getId())
				.param("job_id", "123")
				.param("job_description", "Description")
				.param("employer", "Employer1")
				.param("location", "Joblocation")
				.param("start_date", (Date.valueOf("2019-01-11")).toString())
				.param("end_date", (Date.valueOf("2019-01-22")).toString())
				.param("work_permit", Boolean.toString(true))
				)
		.andExpect(status().isForbidden());
	}
	
	@Test 
	@WithMockUser(username = "first.last1@mail.mcgill.ca")
	public void testFetchApplicationFormForbidden() throws Exception{
		mockMvc.perform(get("/api/internships/{internship_id}/application_form", mockInternship.getId()))
		 .andDo(print())
		 .andExpect(status().isForbidden());
	}
	
	

}
