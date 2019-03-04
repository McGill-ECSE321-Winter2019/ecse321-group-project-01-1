package ca.mcgill.ecse321.backend;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.ReminderRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;
//import 
//ca.mcgill.ecse321.backend.BackendApplicationController;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Arrays;

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