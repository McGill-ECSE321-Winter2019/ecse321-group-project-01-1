package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private InternshipService internshipService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private Student student;

    private Course course;
    private Course course2;

    @Before
    public void setMockCourse(){
        internshipRepository.deleteAll();
        studentRepository.deleteAll();
        courseRepository.deleteAll();
        student = studentService.create(new StudentDto("1111111","john","dow","john.doe@mail.mcgill.ca", "passsword"));
        course  = new Course();
        course.setCourseID("ECSE321");
        course.setId(1);
    }

    @Test
    public void createCourse(){
        assertEquals(0, courseService.getAll().size());
        Course createdCourse = courseService.create(courseService.toDto(course));
        assertEquals(1,courseService.getAll().size());

        assertEquals(course.getId(),createdCourse.getId());
    }

    @Test
    public void findCourseByIdTest(){
        Course createdCourse = courseService.create(courseService.toDto(course));

        Course queriedCourse = courseService.findCourseById(createdCourse.getId());

        assertEquals(queriedCourse,createdCourse);
    }

    @Test
    public void update(){
        Course createdCourse = courseService.create(courseService.toDto(course));

        Course queriedCourse = courseService.findCourseById(createdCourse.getId());
        queriedCourse.setCourseID("ECSE211");

        courseService.update(queriedCourse);

        Course updatedCourse = courseService.findCourseById(createdCourse.getId());

        assertEquals("ECSE211",updatedCourse.getCourseID());
        assertEquals(1,courseService.getAll().size());
    }

    public void testInternshipUpdate() throws Exception {
        Course createdCourse = courseService.create(courseService.toDto(course));
        assertEquals(new HashSet<>(),createdCourse.getInternship());

        Internship internship = internshipService.createInternship(new InternshipDto(AcademicSemester.SUMMER), student, createdCourse);

        Course queriedCourse = courseService.findCourseById(createdCourse.getId());
        Set<Internship> foundInternships = queriedCourse.getInternship();

        assertNotNull(foundInternships);
        assertEquals(1,foundInternships.size());
    }

    @Test
    public void toDto(){
        CourseDto courseDto = courseService.toDto(course);
        assertEquals(courseDto.getCourseID(),course.getCourseID());
        assertEquals(courseDto.getId(),course.getId());
    }



}