package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StorageServiceTest {


    @Autowired
    private BackendApplicationService service;

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

    MockMultipartFile mockMultipartFile1 =
            new MockMultipartFile("files", "FileUploadTest.txt", "text/plain", "This is a Test".getBytes());

    // Another mock file with the same name for testing
    MockMultipartFile mockMultipartFile2 =
            new MockMultipartFile("files", "FileUploadTest.txt", "text/plain", "Hello World!".getBytes());

    MockMultipartFile mockMultipartFile3 =
            new MockMultipartFile("some_file", "RandomFile.txt", "text/plain", "Hello Stupid".getBytes());

    @Before
    public void clear(){
        studentRepository.deleteAll();
        documentRepository.deleteAll();
        applicationFormRepository.deleteAll();
        reminderRepository.deleteAll();
    }


    @Test
    public void testUploadExistence(){
        // make sure it starts empty
        assertEquals(0, service.readAllDocuments().size());

        Student student = service.createStudent("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword");
        Course course = service.createCourse("FACC300");
        Internship internship = service.createInternship(student,course);
        Document document = storageService.createFile(mockMultipartFile1,internship,DocumentType.CONTRACT);

        assertEquals(1,service.readAllDocuments().size());

    }

    @Test
    public void testUploadSameName(){

        assertEquals(0, service.readAllDocuments().size());

        Student student = service.createStudent("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword");
        Course course = service.createCourse("FACC300");
        Internship internship = service.createInternship(student,course);

        Document document = storageService.createFile(mockMultipartFile1,internship,DocumentType.CONTRACT);
        Document document1 = storageService.createFile(mockMultipartFile2,internship,DocumentType.EVALUATION);

        assertEquals(2,service.readAllDocuments().size());

    }
}
