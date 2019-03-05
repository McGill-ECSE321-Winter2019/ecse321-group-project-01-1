package ca.mcgill.ecse321.backend.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
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
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.sql.Date;

 
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ApplicationFormControllerTest {
	
	private static final String JOB_ID1 = "Job1";
	private static final String JOB_ID2 = "Job2";
//	private static MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
	
	private MockMvc mockMvc;
	
	//@Autowired
	//private BackendApplicationService serviceMock;
	@InjectMocks
	private BackendApplicationService service;
	
	@Autowired
	private ApplicationFormRepository appformRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
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
	
	@Test //(expected = NestedServletException.class)
	public void getapplicationTest() throws Exception{
		
		Internship internship = mock(Internship.class);
		Date startDate = Date.valueOf("2019-01-11");
		Date endDate = Date.valueOf("2019-01-22");
		ApplicationForm form1 = new ApplicationForm(JOB_ID1, "Description1", internship, "Employer1", "Location1", startDate, endDate, true);
		form1.setId(1);
		
		when(service.readApplicationForm(1)).thenReturn(form1);
		mockMvc.perform(get("/get_application/{id}", 1))
		 .andExpect(status().isOk())
		 .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		 .andExpect(jsonPath("$.id", is(1)))
		 .andExpect(jsonPath("$.jobDescription", is("Description1")))
		 .andExpect(jsonPath("$.jobId", is("Job1")))
		 .andExpect(jsonPath("$.internship", is("internship")))
		 .andExpect(jsonPath("$.employer", is("Employer1")))
		 .andExpect(jsonPath("$.location", is("Location1")))
		 .andExpect(jsonPath("$.jobId", is("JOB_ID1")))
		 .andExpect(jsonPath("$.startDate", is(startDate)))
		 .andExpect(jsonPath("$.endDate", is(endDate)))
		 .andExpect(jsonPath("$.workPermit", is(true)));
		verify(service, times(1)).readApplicationForm(1);
	    verifyNoMoreInteractions(service);
		
	}

}
