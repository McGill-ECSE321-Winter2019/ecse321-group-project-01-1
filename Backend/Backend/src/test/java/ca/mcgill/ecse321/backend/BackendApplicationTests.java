package ca.mcgill.ecse321.backend;

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
import 
ca.mcgill.ecse321.backend.BackendApplicationController;
import ca.mcgill.ecse321.backend.model.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApplicationTests {
	
	@Mock
	private ApplicationFormRepository applicationDao;
	@Mock
	private CourseRepository coursedDao;
	@Mock
	private DocumentRepository documentDao;
	@Mock
	private InternshipRepository internshipDao;
	@Mock
	private ReminderRepository remainderDao;
	@Mock
	private StudentRepository studentDao;
	
	@Autowired
	private BackendApplicationService service;
	
	@InjectMocks
	private BackendApplicationController controller;
	
	private Student student;
	private static final String STUDENT_KEY = "TestStudent";
	private static final String NONEXISTING_KEY = "NotAStudent";
	
	@Before
	public void setMockOutput() {
	  when(service.createStudent(anyString(), anyString(), anyString(), anyString(), anyString())).thenAnswer( (InvocationOnMock invocation) -> {
	    if(invocation.getArgument(0).equals(STUDENT_KEY)) {
	      Student student = new Student();
	      student.setStudentID(STUDENT_KEY);
	      return student;
	    } else {
	      return null;
	    }
	  });
	}
	
	@Before
	public void setupMock() {
		student = mock(Student.class);
	}

	@Test
	public void testMockPersonCreation() {
		assertNotNull(student);
	}

	@Test
	public void testParticipantQueryFound() {
	  assertEquals(STUDENT_KEY, service.readStudent(STUDENT_KEY).getStudentID());
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

