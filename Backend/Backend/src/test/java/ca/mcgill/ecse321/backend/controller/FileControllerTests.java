package ca.mcgill.ecse321.backend.controller;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.InternshipService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class FileControllerTest {
	
	private Internship internship;
	private Student mockstudent;
	private Course mockcourse;
	private AcademicSemester academicSemester;
	private ApplicationForm applicationForms;
	private Set<Document> documents;
	
	@Autowired
	private InternshipDto internshipDto;
	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private DocumentRepository documentRepository;
	
	@Autowired
	private InternshipService internshipService;

	private MockMvc mockMvc;
	
	public void clearDatabase() {
		// this should be enough because of the composition
		documentRepository.deleteAll();
	}
	
	@Before
	public void setup() throws Exception {
		clearDatabase();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		internshipDto.setAcademicSemester(academicSemester);
		internshipDto.setApplicationForm(applicationForms);
		internshipDto.setDocument(documents);
		internship = internshipService.createInternship(internshipDto, mockstudent, mockcourse);
		internship.setId(5);
	}
	
	@Test
	public void testUploadFile() throws Exception {
		MultipartFile file = null;
		this.mockMvc.perform(post("/api/internships/{internship_id}/documents", internship.getId())
				.param("MultipartFile", String.valueOf(file))
                .param("DcoumenType", String.valueOf(DocumentType.CONTRACT))
                .param("internship", String.valueOf(internship))				
				)
		.andExpect(status().isOk());
	}


}
