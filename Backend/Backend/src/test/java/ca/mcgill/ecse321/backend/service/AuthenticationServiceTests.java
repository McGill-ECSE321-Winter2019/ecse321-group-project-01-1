package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationServiceTests {
	
	private static final String STUDENT_EMAIL = "last.first@mail.mcgill.ca";
	private static final String STUDENT_ID = "260123456";
	
	@InjectMocks
	private AuthenticationService authenticationService;
	
    @Autowired
	private PasswordEncoder passwordEncoder;
    
	@Mock
	private StudentRepository studentRepository;
	
    private Student mockStudent;

	
	public Student createMock() {
		Student student = new Student();
		student.setEmail(STUDENT_EMAIL);
		student.setFirstName("Firstname");
		student.setStudentID(STUDENT_ID);
		student.setLastName("Lastname");
		student.setPassword(passwordEncoder.encode("123456"));
		return student;
	}
	
	@Before
	public void setUp() throws Exception {
		this.mockStudent = createMock();
		when(studentRepository.findStudentByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(STUDENT_EMAIL)) {
				return mockStudent;
			} else {
				return null;
			}
		});
	}
	
	@Test
	@WithMockUser(username = STUDENT_EMAIL, password = "pwd")
	public void loadCurrentUser() {
		Student student = authenticationService.getCurrentStudent();
		assertEquals(mockStudent, student);
		assertNotNull(student);
	}
	
	@Test
	@WithAnonymousUser
	public void loadNullCurrentUser() {
		Student student = authenticationService.getCurrentStudent();
		assertNull(student);
	}
	
}
