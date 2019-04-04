package ca.mcgill.ecse321.backend.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

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

import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.ApplicationFormService;
import ca.mcgill.ecse321.backend.service.CourseService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StudentService;

 
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationFormControllerTests {
	
	private MockMvc mockMvc;
	
	private Student mockStudent;
	
	private Student mockStudent2;
	
	private Course mockCourse;
	
	private Internship mockInternship;
	
	private Internship mockInternship2;

		
	@Autowired
	private ApplicationFormRepository applicationFormRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private InternshipRepository internshipRepository;
	
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
		internshipDto.setYear(2019);
		mockInternship = internshipService.create(internshipDto, mockStudent, mockCourse);
		
		mockInternship2 = internshipService.create(new InternshipDto(2019, AcademicSemester.WINTER), mockStudent2, mockCourse);
		
		
	
		Date startDate = Date.valueOf("2019-01-11");
		Date endDate = Date.valueOf("2019-01-22");
		
		ApplicationFormDto applicationFormDto = new ApplicationFormDto("1111111", "Description1", "Employer1", "Location1", startDate, endDate, true, "abc@mail.com");
		mockApplicationForm = applicationFormService.create(applicationFormDto, mockInternship);
	}
	
	public void clearDatabase() {
		// this should be enough because of the composition
		studentRepository.deleteAll();
		courseRepository.deleteAll();
	}
	
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testCreateApplicationFormDuplicate() throws Exception {
		this.mockMvc.perform(post("/api/internships/{internship_id}/application_form", mockInternship.getId())
				.param("job_id", "123")
				.param("job_description", "Description")
				.param("employer", "Employer1")
				.param("location", "Joblocation")
				.param("start_date", (Date.valueOf("2019-01-11")).toString())
				.param("end_date", (Date.valueOf("2019-01-22")).toString())
				.param("work_permit", Boolean.toString(true))
				.param("employer_email", "abc@mail.com")
				)
		.andExpect(status().isBadRequest());
	}
	
	@Test
	@WithMockUser(username = "first.last1@mail.mcgill.ca")
	public void testCreateApplicationForm() throws Exception {
		this.mockMvc.perform(post("/api/internships/{internship_id}/application_form", mockInternship2.getId())
				.param("job_id", "123")
				.param("job_description", "Description")
				.param("employer", "Employer1")
				.param("location", "Joblocation")
				.param("start_date", (Date.valueOf("2019-01-11")).toString())
				.param("end_date", (Date.valueOf("2019-01-22")).toString())
				.param("work_permit", Boolean.toString(true))
						.param("employer_email", "abc@mail.com")

				)
		.andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testUpdateApplicationForm() throws Exception {
		this.mockMvc.perform(put("/api/internships/{internship_id}/application_form", mockInternship.getId())
				.param("job_id", "1234")
				.param("job_description", mockApplicationForm.getJobDescription())
				.param("employer", mockApplicationForm.getEmployer())
				.param("location", mockApplicationForm.getLocation())
				.param("start_date", mockApplicationForm.getStartDate().toString())
				.param("end_date", mockApplicationForm.getEndDate().toString())
				.param("work_permit", Boolean.toString(mockApplicationForm.isWorkPermit()))
				.param("employer_email", mockApplicationForm.getEmployerEmail()))
		.andExpect(status().isOk())
		 .andExpect(jsonPath("$.job_description", is(mockApplicationForm.getJobDescription())))
		 .andExpect(jsonPath("$.job_id", is("1234")))
		 .andExpect(jsonPath("$.employer", is(mockApplicationForm.getEmployer())))
		 .andExpect(jsonPath("$.location", is(mockApplicationForm.getLocation())))
		 .andExpect(jsonPath("$.start_date", is(mockApplicationForm.getStartDate().toString())))
		 .andExpect(jsonPath("$.end_date", is(mockApplicationForm.getEndDate().toString())))
		 .andExpect(jsonPath("$.work_permit", is(mockApplicationForm.isWorkPermit())))
		;
	}
	
	@Test 
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void getApplicationTest() throws Exception{
		mockMvc.perform(get("/api/internships/{internship_id}/application_form", mockInternship.getId()))
		 .andDo(print())
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.job_description", is(mockApplicationForm.getJobDescription())))
		 .andExpect(jsonPath("$.job_id", is(mockApplicationForm.getJobID())))
		 .andExpect(jsonPath("$.employer", is(mockApplicationForm.getEmployer())))
		 .andExpect(jsonPath("$.location", is(mockApplicationForm.getLocation())))
		 .andExpect(jsonPath("$.start_date", is(mockApplicationForm.getStartDate().toString())))
		 .andExpect(jsonPath("$.end_date", is(mockApplicationForm.getEndDate().toString())))
		 .andExpect(jsonPath("$.work_permit", is(true)))
		.andExpect(jsonPath("$.employer_email", is(mockApplicationForm.getEmployerEmail())));
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
