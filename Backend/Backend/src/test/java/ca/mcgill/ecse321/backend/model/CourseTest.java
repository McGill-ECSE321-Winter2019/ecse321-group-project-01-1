package ca.mcgill.ecse321.backend.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
	
	@InjectMocks
	private Course course; //entity to test on
	@Mock
	private Internship dummyInternship;

	private static final int ID = 678;
	private static final String COURSE_ID = "123";
	
	private Set<Internship> internshipSet = new HashSet<Internship>();
	
	@Before
	public void setupSet() {
		internshipSet.add(dummyInternship);
	}

	@Before
	public void setCourse() {
		course.setInternship(dummyInternship);
		course.setId(ID);
		course.setCourseID(COURSE_ID);
	}
	
	
	@Test
	public void getId() {
		assertEquals(ID,course.getId());
	}
	
	@Test
	public void getCourseId() {
		assertEquals(COURSE_ID,course.getCourseID());
	}

	
	@Test
	public void getInternship() {
		assertEquals(internshipSet,course.getInternship());
	}
	

	
	
}