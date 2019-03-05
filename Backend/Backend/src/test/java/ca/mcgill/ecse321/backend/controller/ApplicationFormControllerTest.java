package ca.mcgill.ecse321.backend.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	private static MediaType CONTENT_TYPE = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype());
	
	private MockMvc mockMvc;
	
	@Autowired
	private BackendApplicationService serviceMock;
	
	@Autowired
	private ApplicationFormRepository appformRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		
		Internship internship = mock(Internship.class);
		Date startDate = Date.valueOf("2019-01-11");
		Date endDate = Date.valueOf("2019-01-22");
		ApplicationForm form1 = new ApplicationForm(JOB_ID1, "Description1", internship, "Employer1", "Location1", startDate, endDate, true);
		
		Internship internship2 = mock(Internship.class);
		Date startDate2 = Date.valueOf("2019-02-11");
		Date endDate2 = Date.valueOf("2019-02-22");
		ApplicationForm form2 = new ApplicationForm(JOB_ID2, "Description2", internship2, "Employer2", "Location2", startDate2, endDate2, false);
	}
	
	@Test(expected = NestedServletException.class)
	public void postapplicationTest() {
		mockMvc.perform(get("/get_application"))
		.andExpect(status().isNotFound())
		.andExpect(content().contentType(CONTENT_TYPE));
		
	}

}
