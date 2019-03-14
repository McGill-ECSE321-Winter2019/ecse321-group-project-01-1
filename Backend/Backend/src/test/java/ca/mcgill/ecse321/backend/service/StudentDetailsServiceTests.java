package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.config.SecurityTestConfiguration;
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
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {SecurityTestConfiguration.class})
public class StudentDetailsServiceTests {
	
	@Mock
	private StudentRepository studentRepository;
	
	private static final String STUDENT_EMAIL = "last.first@mail.mcgill.ca";
	private static final String STUDENT_ID = "260123456";

    @Autowired
	private PasswordEncoder passwordEncoder;
    
    @InjectMocks
    private StudentDetailsService studentDetailsService;

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
    public void loadValid() throws Exception {
    	User user = (User) studentDetailsService.loadUserByUsername(STUDENT_EMAIL);
    	assertEquals(mockStudent.getEmail(), user.getUsername());
    	assertEquals(mockStudent.getPassword(), user.getPassword());
    }
    
    @Test(expected = UsernameNotFoundException.class)
    public void loadInValid() {
    	User user = (User) studentDetailsService.loadUserByUsername("Invalid");
    	fail();
    }

    
}


