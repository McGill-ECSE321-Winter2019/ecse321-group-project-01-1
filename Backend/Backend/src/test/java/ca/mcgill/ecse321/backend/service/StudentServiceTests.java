package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ca.mcgill.ecse321.backend.config.SecurityTestConfiguration;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Student;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {
	
	private static final String STUDENT_EMAIL = "last.first@mail.mcgill.ca";
	private static final String STUDENT_ID = "260123456";

    
	@Autowired
	private StudentRepository studentRepository;
    
	@Autowired
    private StudentService studentService;
	
	@PersistenceContext
	private EntityManager entityManager;
    
	@Before
	public void clearDatabase() {
		studentRepository.deleteAll();
	}
    
    
	public StudentDto createMock() {
		StudentDto studentDto = new StudentDto();
		studentDto.setEmail(STUDENT_EMAIL);
		studentDto.setFirstName("Firstname");
		studentDto.setStudentID(STUDENT_ID);
		studentDto.setLastName("Lastname");
		studentDto.setPassword("123456");
		return studentDto;
	}
	
	public StudentDto createMock2() {
		StudentDto studentDto = new StudentDto();
		studentDto.setEmail(STUDENT_EMAIL);
		studentDto.setFirstName("Firstname2");
		studentDto.setStudentID(STUDENT_ID+"2");
		studentDto.setLastName("Lastname2");
		studentDto.setPassword("123456");
		return studentDto;
	}

	public StudentDto createMock3() {
		StudentDto studentDto = new StudentDto();
		studentDto.setEmail(STUDENT_EMAIL+"2");
		studentDto.setFirstName("Firstname3");
		studentDto.setStudentID(STUDENT_ID);
		studentDto.setLastName("Lastname3");
		studentDto.setPassword("123456");
		return studentDto;
	}
    
    @Test
    @Transactional
    public void createValidUser() throws Exception {
    	Student student = studentService.create(createMock());
    	student = studentService.findStudentByStudentID(STUDENT_ID);
    	assertNotNull(student);
    }
    
    @Test(expected = Exception.class)
    @Transactional
    public void createDuplicateUserEmail() throws Exception {
    	studentService.create(createMock());
    	Student student = student = studentService.findStudentByStudentID(STUDENT_ID);
    	assertNotNull(student);
    	studentService.create(createMock2());
    	fail();	
    }
    
    @Test(expected = javax.persistence.EntityExistsException.class)
    @Transactional
    public void createDuplicateUserID() throws Exception {
    	//
    	studentService.create(createMock());
    	Student student = studentService.findStudentByStudentID(STUDENT_ID);
    	assertNotNull(student);
    	studentService.create(createMock3());
    	fail();	
    }
    
    @Test(expected = javax.validation.ConstraintViolationException.class)
    @Transactional
    public void createEmptyUserEmail() throws Exception {
    	//
    	StudentDto studentDto = createMock();
    	studentDto.setEmail("");
    	studentDto.setFirstName("");
    	studentService.create(studentDto);
    	fail();	
    }
    
}


