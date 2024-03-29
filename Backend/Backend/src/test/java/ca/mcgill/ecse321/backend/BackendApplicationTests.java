package ca.mcgill.ecse321.backend;

import ca.mcgill.ecse321.backend.dao.*;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {
	
	@Mock
	private ApplicationFormRepository applicationDao;
	@Mock
	private CourseRepository courseDao;
	@Mock
	private DocumentRepository documentDao;
	@Mock
	private InternshipRepository internshipDao;
	@Mock
	private ReminderRepository reminderDao;
	@Mock
	private StudentRepository studentDao;
	
	@InjectMocks
	private StudentService studentService;
	@InjectMocks
	private InternshipService internshipService;
	@InjectMocks
	private ApplicationFormService applicationFormService;
	@InjectMocks
	private StorageService storageService;
	@InjectMocks
	private ReminderService reminderService;
	@InjectMocks
	private CourseService courseService;
	
	@InjectMocks
//	private BackendApplicationController controller;
	
	private Student student;
	private static final String STUDENT_KEY = "TestStudent";
	private static final String NONEXISTING_KEY = "NotAStudent";
	
	private Document document;
	private static final String DOCUMENT_KEY = "ABCD-efgh";
	
	private Internship internship;
	private static final int INTERSHIP_KEY = 25;
	
	private ApplicationForm applicationform;
	private static final int FORM_KEY = 50;
	
	private Reminder reminder;
	private static final int REMINDER_KEY = 65;
	
	private Course course;
	private static final int COURSE_KEY = 70;
	
	
	@Before
	public void setMockOutput() {
	  when(studentDao.findStudentByStudentID(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0).equals(STUDENT_KEY)) {
	      Student student = new Student();
	      student.setStudentID(STUDENT_KEY);
	      return student;
	    } else {
	      return null;
	    }
	  });
	  
	  when(documentDao.findDocumentById(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
		    if(invocation.getArgument(0).equals(DOCUMENT_KEY)) {
		      Document document = new Document();
		      document.setId(DOCUMENT_KEY);
		      return document;
		    } else {
		      return null;
		    }
		  });
	  
	  when(internshipDao.findInternshipById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
		    if(invocation.getArgument(0).equals(INTERSHIP_KEY)) {
		      Internship internship = new Internship();
		      internship.setId(INTERSHIP_KEY);
		      return internship;
		    } else {
		      return null;
		    }
		  });
	  
	  when(applicationDao.findApplicationFormById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
		    if(invocation.getArgument(0).equals(FORM_KEY)) {
		      ApplicationForm applicationform = new ApplicationForm();
		      applicationform.setId(FORM_KEY);
		      return applicationform;
		    } else {
		      return null;
		    }
		  });
	  
	  when(reminderDao.findReminderById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
		    if(invocation.getArgument(0).equals(REMINDER_KEY)) {
		      Reminder reminder = new Reminder();
		      reminder.setId(REMINDER_KEY);
		      return reminder;
		    } else {
		      return null;
		    }
		  });
	  
	  when(courseDao.findCourseById(anyInt())).thenAnswer( (InvocationOnMock invocation) -> {
		    if(invocation.getArgument(0).equals(COURSE_KEY)) {
		      Course course = new Course();
		      course.setId(COURSE_KEY);
		      return course;
		    } else {
		      return null;
		    }
		  });
		  
	}
	  
	@Before
	public void setupMock() {
		student = mock(Student.class);
		document = mock(Document.class);
		internship = mock(Internship.class);
		applicationform = mock(ApplicationForm.class);
		reminder = mock(Reminder.class);
		course = mock(Course.class);
	}

	@Test
	public void testMockObjectCreation() {
		assertNotNull(student);
		assertNotNull(document);
		assertNotNull(internship);
		assertNotNull(applicationform);
		assertNotNull(reminder);
		assertNotNull(course);
	}

	@Test
	public void testParticipantQueryFound() {
	  assertEquals(STUDENT_KEY, studentService.findStudentByStudentID(STUDENT_KEY).getStudentID());
	}

	@Test
	public void testDocumentQueryFound() {
		assertEquals(DOCUMENT_KEY, storageService.readDocument(DOCUMENT_KEY).getId());
	}
	
	@Test
	public void testInternshipQueryFound() {
		assertEquals(INTERSHIP_KEY, internshipService.findInternshipById(INTERSHIP_KEY).getId());
	}
	
	@Test
	public void testApplicationFormQueryFound() {
		assertEquals(FORM_KEY, applicationFormService.findApplicationFormById(FORM_KEY).getId());
	}
	
	@Test
	public void testReminderQueryFound() {
		assertEquals(REMINDER_KEY, reminderService.findReminderById(REMINDER_KEY).getId());
	}
	
	@Test
	public void testCourseQueryFound() {
		assertEquals(COURSE_KEY, courseService.findCourseById(COURSE_KEY).getId());
	}
	
	//@Test
	//public void testgetAllStudent() {
	//	List<Student> list = Arrays.asList(service.createStudent("12345", "Jack", "Black", "jack.black@mail.mcgill.ca", "54321"),
	//			service.createStudent("13579", "Brain", "White", "brain.white@mail.mcgill.ca", "97531"));
	//}
	

	@Test
	public void contextLoads() {
	}

}

