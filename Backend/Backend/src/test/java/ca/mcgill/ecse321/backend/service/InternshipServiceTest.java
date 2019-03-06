package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

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
        student = studentService.create(new StudentDto("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword"));
        course = courseService.create(new CourseDto("FACC300"));
        mockInternship = new Internship(course,AcademicSemester.FALL,student);
        mockInternship.setId(1);
    }

    @Test
    @Transactional
    public void createInternship()throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship internship = internshipService.createInternship(new InternshipDto(AcademicSemester.SUMMER), student, course);
        assertEquals(1, internshipService.getAll().size());
        assertEquals(mockInternship, internship);
    }

    @Test
    public void findByIdAndStudentStudentID()throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship createdInternship = internshipService.createInternship(new InternshipDto(AcademicSemester.SUMMER), student, course);
        Internship queriedInternship = internshipService.findByIdAndStudentStudentID(createdInternship.getId(),"1111111");

        assertEquals(createdInternship,queriedInternship);
    }

    @Test
        public void findByIdAndStudent() throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship createdInternship = internshipService.createInternship(new InternshipDto(AcademicSemester.SUMMER), student, course);
        Internship queriedInternship = internshipService.findByIdAndStudent(createdInternship.getId(),student);

        assertEquals(createdInternship,queriedInternship);
    }

    @Test
    public void findById() throws Exception {
        assertEquals(0, internshipService.getAll().size());
        Internship createdInternship = internshipService.createInternship(new InternshipDto(AcademicSemester.SUMMER), student, course);
        Internship queriedInternship = internshipService.findInternshipById(createdInternship.getId());

        assertEquals(createdInternship,queriedInternship);
    }

    @Test
    public void toDto() {
        InternshipDto dto = internshipService.toDto(mockInternship);
        assertEquals(AcademicSemester.FALL,dto.getAcademicSemester());
        assertEquals(student,dto.getStudent());
        assertEquals(courseService.toDto(course),dto.getCourse());
    }
}
