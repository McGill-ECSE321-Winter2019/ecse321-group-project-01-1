package ca.mcgill.ecse321.backend.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.DocumentDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.CourseService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StorageService;
import ca.mcgill.ecse321.backend.service.StudentService;

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
		documentRepository.deleteAll();
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
		
	//	DocumentDto documentdto = new DocumentDto("filename", "path", "filrtype", 1L, DocumentType.CONTRACT);
	    FileInputStream fis=new FileInputStream("/Users/hezirui/Documents/~$ck 100 poster.docx");
	    MockMultipartFile upload= new MockMultipartFile("upload",fis);
		mockDocument = storageService.createFile(upload, mockInternship, DocumentType.CONTRACT);
	}
	
	@Test
	@WithMockUser(username = "first.last@mail.mcgill.ca")
	public void testUploadFile() throws Exception {
		
	    FileInputStream path=new FileInputStream("/Users/hezirui/Desktop/View submitted Documents.docx");
	    MockMultipartFile file= new MockMultipartFile("file",path);
	//	this.mockMvc.perform(post("/api/internships/{internship_id}/documents", mockInternship.getId())
	    mockMvc.perform(MockMvcRequestBuilders.multipart("/api/internships/{internship_id}/documents", mockInternship.getId())
				.file(file)
			//s	.param("MultipartFile", String.valueOf(file))
                .param("DcoumenType", (DocumentType.CONTRACT).toString())
                .param("internship", (mockInternship).toString())				
				)
		.andExpect(status().isOk());
	}


}
