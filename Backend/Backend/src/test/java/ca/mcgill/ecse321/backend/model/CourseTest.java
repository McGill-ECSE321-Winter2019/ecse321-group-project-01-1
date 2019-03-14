package ca.mcgill.ecse321.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {
	
	private Course course = new Course(); //entity to test on
	private Internship dummyInternship = new Internship();

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