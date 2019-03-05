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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
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
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.ReminderService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.sql.Date;
import java.util.Set;

 
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationFormControllerTest {
	
	private static final int ID1 = 1;
//	private static final String JOB_ID2 = "Job2";
//	private static MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
	
	private MockMvc mockMvc;
	
	//@Autowired
	//private BackendApplicationService serviceMock;
	@InjectMocks
	private ApplicationFormService service;
	private Internship internship;
	
	@Autowired 
	private InternshipDto internshipDto;
	
	@Autowired
	private Student mocstudent;
	
	@Autowired
	private Course mockcourse;
	
	@Autowired
	private ApplicationFormRepository appformRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private InternshipService internshipService;
	
	
	@Before
	public void init() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
/*		
		Internship internship = mock(Internship.class);
		InternshipDto internshipDto = new InternshipDto();
		internshipDto.setId(123);
		internshipDto.setCourse(mock(CourseDto.class));
		internshipDto.setApplicationForm(mock(ApplicationForm.class));
		internshipDto.setDocument((Set<Document>) mock(Document.class));
		internshipDto.setAcademicSemester(mock(AcademicSemester.class));
		internshipDto.setStudent(mock(Student.class));
		internshipDto.setProgress(new boolean[5]);
*/		
		

		internship.setId(22);
		
		
/*		
		Internship internship = mock(Internship.class);
		Date startDate = Date.valueOf("2019-01-11");
		Date endDate = Date.valueOf("2019-01-22");
		ApplicationForm form1 = new ApplicationForm(JOB_ID1, "Description1", internship, "Employer1", "Location1", startDate, endDate, true);
		
		Internship internship2 = mock(Internship.class);
		Date startDate2 = Date.valueOf("2019-02-11");
		Date endDate2 = Date.valueOf("2019-02-22");
		ApplicationForm form2 = new ApplicationForm(JOB_ID2, "Description2", internship2, "Employer2", "Location2", startDate2, endDate2, false);
*/
	}
	
	@Test
	public void testCreateApplicationForm() throws Exception {
		this.mockMvc.perform(post("/api/internships/{internship_id}/application_form", internship.getId())
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
	
	@Test //(expected = NestedServletException.class)
	public void getapplicationTest() throws Exception{
		
		Internship internship = mock(Internship.class);
		internship.setId(3);
		Date startDate = Date.valueOf("2019-01-11");
		Date endDate = Date.valueOf("2019-01-22");
		ApplicationForm form1 = new ApplicationForm("Job1", "Description1", internship, "Employer1", "Location1", startDate, endDate, true);
		form1.setId(ID1);
		
	//	when(service.findApplicationFormById(ID1)).thenAnswer( (InvocationOnMock invocation) -> {
	//		return form1;
	//	});
		
		mockMvc.perform(get("/api/internships/{internship_id}/application_form", internship.getId()))
		 .andDo(print())
		 .andExpect(status().isOk())
		 .andExpect(jsonPath("$.id", is(ID1)))
		 .andExpect(jsonPath("$.jobDescription", is("Description1")))
		 .andExpect(jsonPath("$.jobID", is("Job1")))
		 .andExpect(jsonPath("$.internship", is("internship")))
		 .andExpect(jsonPath("$.employer", is("Employer1")))
		 .andExpect(jsonPath("$.location", is("Location1")))
		 .andExpect(jsonPath("$.jobId", is("Job1")))
		 .andExpect(jsonPath("$.startDate", is(startDate)))
		 .andExpect(jsonPath("$.endDate", is(endDate)))
		 .andExpect(jsonPath("$.workPermit", is(true)));
		verify(service, times(1)).findApplicationFormById(ID1);
	    verifyNoMoreInteractions(service);
		
	}

}
