package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationFormServiceTest {
    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ApplicationFormRepository applicationFormRepository;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ApplicationFormService applicationFormService;

    private ApplicationForm mockapplicationForm;

    private Student student;

    private Course course;

    private HashSet<Document> mockDocuments;

    private Internship internship;

    @Before
    public void setMockapplicationForm() throws Exception{
        internshipRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        applicationFormRepository.deleteAll();
        student = studentService.create(new StudentDto("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword"));
        course = courseService.create(new CourseDto("FACC300"));

        internship = internshipService.createInternship(internshipService.toDto(new Internship(course,AcademicSemester.FALL,student)),student,course);

        Date startDate = new Date(1);
        Date endDate = new Date(2);
        mockapplicationForm = new ApplicationForm("someID", "writing code", internship, "Google",  "planet", startDate, endDate, true);

        // in real application the only way to create would be through service
        mockapplicationForm.setId(1);
    }

    @Test
    public void createApplicationForm() {
        assertEquals(0,applicationFormService.getAll().size());
        ApplicationForm createdForm = applicationFormService.createApplicationForm(applicationFormService.toDto(mockapplicationForm),internship);
        assertEquals(1,applicationFormService.getAll().size());

        assertEquals(mockapplicationForm,createdForm);
    }

    @Test
    public void findApplicationFormById() {
        assertEquals(0,applicationFormService.getAll().size());
        ApplicationForm createdForm = applicationFormService.createApplicationForm(applicationFormService.toDto(mockapplicationForm),internship);
        assertEquals(1,applicationFormService.getAll().size());

        ApplicationForm queriedForm = applicationFormService.findApplicationFormById(createdForm.getId());

        assertEquals(mockapplicationForm,queriedForm);
    }

    @Test
    public void updateForm(){
        assertEquals(0,applicationFormService.getAll().size());
        ApplicationForm createdForm = applicationFormService.createApplicationForm(applicationFormService.toDto(mockapplicationForm),internship);
        assertEquals(1,applicationFormService.getAll().size());

        //updating a value
        ApplicationForm queriedForm = applicationFormService.findApplicationFormById(createdForm.getId());
        queriedForm.setJobDescription("This is a terrible job");
        applicationFormService.update(queriedForm);

        ApplicationForm updatedForm = applicationFormService.findApplicationFormById(createdForm.getId());
        assertEquals("This is a terrible job",updatedForm.getJobDescription());
    }

    @Test
    public void toDtoTest() {
        ApplicationFormDto applicationFormDto = applicationFormService.toDto(mockapplicationForm);

        assertEquals(mockapplicationForm.getEmployer(),applicationFormDto.getEmployer());
        assertEquals(mockapplicationForm.getId(),applicationFormDto.getId());
        assertEquals(mockapplicationForm.getJobDescription(),applicationFormDto.getJobDescription());
        assertEquals(mockapplicationForm.getLocation(),applicationFormDto.getLocation());
        assertEquals(mockapplicationForm.getEndDate(),applicationFormDto.getEndDate());
        assertEquals(mockapplicationForm.getStartDate(),applicationFormDto.getStartDate());
    }
}
