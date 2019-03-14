package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.CourseService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StorageService;
import ca.mcgill.ecse321.backend.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileControllerTests {
	
	
    private Student mockStudent;
	
	private Course mockCourse;
	
	private Internship mockInternship;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private InternshipRepository internshipRepository;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private InternshipService internshipService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private StorageService storageService;

	private MockMvc mockMvc;
	
	private Document mockDocument;
	
	public void clearDatabase() {
		// this should be enough because of the composition
		studentRepository.deleteAll();
		courseRepository.deleteAll();
	}
	
	@Before
	public void setup() throws Exception {
		clearDatabase();
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.apply(springSecurity()).build();
		
        mockCourse = courseService.create(new CourseDto("FACC 200"));
		
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentID("260123456");
		studentDto.setEmail("first.last@mail.mcgill.ca");
		studentDto.setPassword("123456");
		studentDto.setFirstName("First");
		studentDto.setLastName("Last");
		mockStudent = studentService.create(studentDto);
		
		InternshipDto internshipDto = new InternshipDto();
		internshipDto.setAcademicSemester(AcademicSemester.SUMMER);
		mockInternship = internshipService.createInternship(internshipDto, mockStudent, mockCourse);
		
	    MockMultipartFile upload = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());
		mockDocument = storageService.createFile(upload, mockInternship, DocumentType.CONTRACT);
	}
	
	@Test
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testUploadFile() throws Exception {
		
	    MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

	    mockMvc.perform(fileUpload("/api/internships/{internship_id}/documents", mockInternship.getId())
				.file(file)
                .param("type", DocumentType.EVALUATION.toString())
				)
		.andExpect(status().isOk());
	}


}
