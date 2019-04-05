package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternshipServiceTest {

    @Autowired
    private InternshipRepository internshipRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private ApplicationForm mockapplicationForm;

    private Student student;

    private Course course;

    private HashSet<Document> mockDocuments;

    private Internship mockInternship;

    @Before
    public void setMockInternship() throws Exception{
        internshipRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        student = studentService.create(new StudentDto("111111111","john","dow","john.doe@mail.mcgill.ca", "passsword"));
        course = courseService.create(new CourseDto("FACC300"));
        mockInternship = new Internship(2019,AcademicSemester.FALL, course, student);
        mockInternship.setStudent(student);
        mockInternship.setCourse(course);
        mockInternship.setId(1);
    }

    @Test
    public void testInitialization(){
        assertEquals(1, mockInternship.getId());
    }

    @Test
    public void createInternship()throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship internship = internshipService.create(new InternshipDto(2019, AcademicSemester.SUMMER), student, course);
        assertEquals(1, internshipService.getAll().size());
        assertEquals(mockInternship, internship);
    }

    @Test
    public void findByIdAndStudentStudentID()throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship createdInternship = internshipService.create(new InternshipDto(2019, AcademicSemester.SUMMER), student, course);
        Internship queriedInternship = internshipService.findByIdAndStudentStudentID(createdInternship.getId(),student.getStudentID());

        assertEquals(createdInternship,queriedInternship);
    }

    @Test
        public void findByIdAndStudent() throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship createdInternship = internshipService.create(new InternshipDto(2019, AcademicSemester.SUMMER), student, course);
        Internship queriedInternship = internshipService.findByIdAndStudent(createdInternship.getId(),student);

        assertEquals(createdInternship,queriedInternship);
    }

    @Test
    public void findById() throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship createdInternship = internshipService.create(new InternshipDto(2019, AcademicSemester.SUMMER), student, course);
        Internship queriedInternship = internshipService.findInternshipById(createdInternship.getId());

        assertEquals(createdInternship,queriedInternship);
    }

    @Test
    public void toDto() {
        InternshipDto dto = internshipService.toDto(mockInternship);
        assertEquals(AcademicSemester.FALL,dto.getAcademicSemester());
        assertEquals(courseService.toDto(course),dto.getCourse());
    }
}
