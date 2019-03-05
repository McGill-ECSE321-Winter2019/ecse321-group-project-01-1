package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import ca.mcgill.ecse321.backend.exception.FileStorageException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.dao.*;
import ca.mcgill.ecse321.backend.dto.StudentDto;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceTest {


    @Autowired
    private BackendApplicationService service;

	@Autowired
	private StudentService studentService;
    
    @Autowired
    private StorageService storageService;

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
        documentRepository.deleteAll();
        applicationFormRepository.deleteAll();
        reminderRepository.deleteAll();
        courseRepository.deleteAll();
        internshipRepository.deleteAll();
        Student student = studentService.createStudent(new StudentDto("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword"));
        Course course = service.createCourse("FACC300");
        this.mockInternship = service.createInternship(student,course);
    }


    @Test
    @Transactional
    public void testUploadExistence(){
        // make sure it starts empty
        assertEquals(0, service.readAllDocuments().size());

        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.CONTRACT);

        assertEquals(1,service.readAllDocuments().size());
    }

    @Test
    @Transactional
    public void testUploadSameName(){

        assertEquals(0, service.readAllDocuments().size());

        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.CONTRACT);
        Document document1 = storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);

        assertEquals(2,service.readAllDocuments().size());
    }

    @Test
    @Transactional
    public void testReUpload(){
        assertEquals(0, service.readAllDocuments().size());

        Document document = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        assertEquals(1,service.readAllDocuments().size());

        Document document1 = storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        assertEquals(1,service.readAllDocuments().size());
    }

    @Test
    @Transactional
    public void testGetAllDocumentsByInternship(){
        assertEquals(0, service.readAllDocuments().size());

        storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.EVALUATION);
        storageService.createFile(mockMultipartFile1,mockInternship,DocumentType.CONTRACT);
        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.TECHNICAL_REPORT);

        assertEquals(3, service.readAllDocuments().size());

        List<Document> documents = storageService.readAllDocumentsByInternship(mockInternship);
        assertEquals(3,documents.size());
    }

    @Test
    @Transactional
    public void testGetDocumentsByInternshipAndType(){
        assertEquals(0, service.readAllDocuments().size());

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
    @Transactional
    public void testInvalidName(){
        assertEquals(0, service.readAllDocuments().size());

        storageService.createFile(invalideFileNameFile,mockInternship,DocumentType.EVALUATION);
        fail();
    }

    @Test
    @Transactional
    public void testGetDocumentById(){
        assertEquals(0, service.readAllDocuments().size());

        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);
        Document document = storageService.readDocumentByType(mockInternship,DocumentType.EVALUATION);
        String id = document.getId();

        Document document1 = storageService.readDocument(id);
        assertEquals("FileUploadTest.txt",document1.getFileName());
    }

    @Test(expected = IllegalArgumentException.class)
    @Transactional
    public void testNullInternshipException(){
        storageService.createFile(mockMultipartFile2,mockInternship,DocumentType.EVALUATION);
        Internship emptyInternship = null;
        List<Document> document = storageService.readAllDocumentsByInternship(emptyInternship);
        fail();
    }
}
