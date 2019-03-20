package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.*;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.DocumentDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.exception.FileStorageException;
import ca.mcgill.ecse321.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceTest {

	@Autowired
	private StudentService studentService;
	@Autowired
	private InternshipService internshipService;
	@Autowired
	private ApplicationFormService applicationFormService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private ReminderService reminderService;
	@Autowired
	private CourseService courseService;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ApplicationFormRepository applicationFormRepository;
    @Autowired
    private ReminderRepository reminderRepository;
    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private CourseRepository courseRepository;

    MockMultipartFile mockMultipartFile1 =
            new MockMultipartFile("files", "FileUploadTest.txt", "text/plain", "This is a Test".getBytes());

    // Another mock file with the same name for testing
    MockMultipartFile mockMultipartFile2 =
            new MockMultipartFile("files", "FileUploadTest.txt", "text/plain", "Hello World!".getBytes());

    MockMultipartFile mockMultipartFile3 =
            new MockMultipartFile("some_file", "RandomFile.txt", "text/plain", "Hello Stupid".getBytes());

    MockMultipartFile mockMultipartFile4 =
            new MockMultipartFile("some_file2", "AmazingFile.txt", "text/plain", "Hello Stupid".getBytes());

    MockMultipartFile brokenMockFile =
            new MockMultipartFile("some_file2", "AmazingFile.txt", "png/plan", "Something".getBytes());

    MockMultipartFile invalideFileNameFile =
            new MockMultipartFile("some_file2", "Amazi^n..gFile.txt", "png/plan", "Something".getBytes());

    private Internship mockInternship;

    @Before
    public void setUp() throws Exception{
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        Student student = studentService.create(new StudentDto("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword"));
        Course course = courseService.create(new CourseDto("FACC300"));
       
        this.mockInternship = internshipService.createInternship(new InternshipDto(AcademicSemester.SUMMER), student, course);
    }


    @Test
    public void testUploadExistence(){
        // make sure it starts empty
        assertEquals(0, storageService.getAll().size());

        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.CONTRACT);

        assertEquals(1,storageService.getAll().size());
    }

    @Test
    public void testUploadSameName(){

        assertEquals(0, storageService.getAll().size());

        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.CONTRACT);
        Document document1 = storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);

        assertEquals(2,storageService.getAll().size());
    }

    @Test
    public void testReUpload(){
        assertEquals(0, storageService.getAll().size());

        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        assertEquals(1,storageService.getAll().size());

        Document document1 = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        assertEquals(1,storageService.getAll().size());
    }

    @Test
    public void testGetAllDocumentsByInternship(){
        assertEquals(0, storageService.getAll().size());

        storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.CONTRACT);
        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.TECHNICAL_REPORT);

        assertEquals(3, storageService.getAll().size());

        List<Document> documents = storageService.readAllDocumentsByInternship(mockInternship);
        assertEquals(3,documents.size());
    }

    @Test
    public void testGetDocumentsByInternshipAndType(){
        assertEquals(0, storageService.getAll().size());

        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);
        storageService.createFile(mockMultipartFile3,mockInternship,DocumentType.CONTRACT);
        storageService.createFile(mockMultipartFile4,mockInternship,DocumentType.TECHNICAL_REPORT);

        Document document = storageService.readDocumentByType(mockInternship,DocumentType.EVALUATION);
        assertEquals("FileUploadTest.txt",document.getFileName());

        Document document1 = storageService.readDocumentByType(mockInternship,DocumentType.CONTRACT);
        assertEquals("RandomFile.txt",document1.getFileName());

        Document document2 = storageService.readDocumentByType(mockInternship,DocumentType.TECHNICAL_REPORT);
        assertEquals("AmazingFile.txt",document2.getFileName());
    }

    @Test(expected = FileStorageException.class)
    public void testInvalidName(){
        assertEquals(0, storageService.getAll().size());

        storageService.createFile(invalideFileNameFile,mockInternship,DocumentType.EVALUATION);
        fail();
    }

    @Test
    public void testGetDocumentById(){
        assertEquals(0, storageService.getAll().size());

        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);
        Document document = storageService.readDocumentByType(mockInternship,DocumentType.EVALUATION);
        String id = document.getId();

        Document document1 = storageService.readDocument(id);
        assertEquals("FileUploadTest.txt",document1.getFileName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInternshipException(){
        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);
        Internship emptyInternship = null;
        List<Document> document = storageService.readAllDocumentsByInternship(emptyInternship);
        fail();
    }

    @Test
    public void toDto(){
        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        DocumentDto documentDto = storageService.toDto(document);

        assertEquals(document.getDocumentType(),documentDto.getDocumentType());
        assertEquals(document.getFileName(),documentDto.getFileName());
        assertEquals(document.getId(),documentDto.getId());
        assertEquals(document.getSize(),documentDto.getSize());
    }
}
