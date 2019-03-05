package ca.mcgill.ecse321.backend.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class StudentControllerTests {
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() throws Exception {
	    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void testCreateStudent() throws Exception {
	    this.mockMvc.perform(post("/external/students")
	    		.param("student_id", "260123456")
	    		.param("first_name", "first")
	    		.param("last_name", "last")
	    		.param("email", "first.last@mail.mcgill.ca")
	    		.param("password", "123456")
	    		)
	      .andExpect(status().isOk());
	}
	
	@Test
	public void testCreateStudentDuplicateID() throws Exception {
	    this.mockMvc.perform(post("/external/students")
	    		.param("student_id", "260123456")
	    		.param("first_name", "first")
	    		.param("last_name", "last")
	    		.param("email", "first.last1@mail.mcgill.ca")
	    		.param("password", "123456")
	    		)
	      .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateStudentDuplicateEmail() throws Exception {
	    this.mockMvc.perform(post("/external/students")
	    		.param("student_id", "260123457")
	    		.param("first_name", "first")
	    		.param("last_name", "last")
	    		.param("email", "first.last@mail.mcgill.ca")
	    		.param("password", "123456")
	    		)
	      .andExpect(status().isBadRequest());
	}
	
	@Test
	public void testCreateStudentMissingParam() throws Exception {
	    this.mockMvc.perform(post("/external/students")
	    		.param("student_id", "260123457")
	    		.param("first_name", "first")
	    		.param("last_name", "last")
	    		.param("email", "first.last@mail.mcgill.ca")
	    		)
	      .andExpect(status().isBadRequest());
	}
	
}
