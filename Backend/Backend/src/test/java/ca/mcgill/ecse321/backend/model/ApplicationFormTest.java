package ca.mcgill.ecse321.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationFormTest {
	
	@InjectMocks
	private ApplicationForm form; //entity to test on
	@Mock
	private Internship dummyInternship = mock(Internship.class);

	private static final int ID = 678;
	private static final String JOB_ID = "12345";
	private static final String JOB_DESCRIPTION = "Work";
	private static final String EMPLOYER = "People";
	private static final String LOCATION = "Earth"; 
	private static final Date START_DATE = new Date(10);
	private static final Date END_DATE = new Date(20);
	private static final boolean WORK_PERMIT = true;
	
	@Before
	public void setForm() {
		form.setId(ID);
		form.setJobID(JOB_ID);
		form.setJobDescription(JOB_DESCRIPTION);
		//form.setInternship(dummyInternship);
		form.setEmployer(EMPLOYER);
		form.setLocation(LOCATION);
		form.setStartDate(START_DATE);
		form.setEndDate(END_DATE);
		form.setWorkPermit(WORK_PERMIT);

	}
		
	@Test
	public void getId() {
		assertEquals(ID,form.getId());
	}
	
	@Test
	public void getJobId() {
		assertEquals(JOB_ID,form.getJobID());
	}
	
	@Test
	public void getJobDescription() {
		assertEquals(JOB_DESCRIPTION,form.getJobDescription());
	}
	
	@Test
	public void getInternship() {
		assertEquals(dummyInternship,form.getInternship());
	}
	
	@Test
	public void getEmployer() {
		assertEquals(EMPLOYER,form.getEmployer());
	}
	
	@Test
	public void getLocation() {
		assertEquals(LOCATION,form.getLocation());
	}
	
	@Test
	public void getStartDate() {
		assertEquals(START_DATE,form.getStartDate());
	}
	
	@Test
	public void getEndDate() {
		assertEquals(END_DATE,form.getEndDate());
	}

	
	@Test
	public void getWorkPermit() {
		assertEquals(WORK_PERMIT, form.isWorkPermit());
	}
	
	
}