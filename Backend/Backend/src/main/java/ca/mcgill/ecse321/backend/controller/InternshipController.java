package ca.mcgill.ecse321.backend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.InternshipDeepDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.CourseService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class InternshipController {

	@Autowired
	private InternshipService internshipService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * This method gets an internship via a GET request
	 * 
	 * @param internshipId Internship ID
	 * @return InternshipDeep DTO
	 */
	@GetMapping(value = { "/api/internships/{internship_id}", "/api/internships/{internship_id}" })
	public InternshipDeepDto getInternship(@PathVariable(value = "internship_id") int internshipId) {
		Student student = authenticationService.getCurrentStudent();
		Internship i = internshipService.findByIdAndStudent(internshipId, student);
		if (i == null)
			throw new AccessDeniedException("");
		return internshipService.deepToDto(i);
	}

	/**
	 * This method gets all internships via a GET request
	 * 
	 * @return List of Internship DTOs
	 */
	@GetMapping(value = { "/api/internships", "/api/internships/" })
	public List<InternshipDto> getInternshipDtos() {
		Student student = authenticationService.getCurrentStudent();

		Set<Internship> internshipList = student.getInternship();
		if (internshipList == null) {
			throw new IllegalArgumentException("There is no such internship!");
		}

		List<InternshipDto> dtoList = new ArrayList<InternshipDto>();
		for (Internship internship : internshipList) {
			dtoList.add(internshipService.toDto(internship));
		}
		return dtoList;
	}

	/**
	 * This method gets all internships via a GET request
	 * 
	 * @return List of Internship DTOs
	 */
	@GetMapping(value = { "/external/internships", "/external/internships/" })
	public List<InternshipDto> getAllInternship() {
		List<Internship> internshipList = internshipService.getAll();

		List<InternshipDto> dtoList = new ArrayList<InternshipDto>();
		for (Internship internship : internshipList) {
			dtoList.add(internshipService.toDto(internship));
		}
		return dtoList;
	}

	/**
	 * This method creates an internship via a POST request
	 * 
	 * @param studentID        Student ID
	 * @param courseID         Course ID
	 * @param academicSemester Academic Semester
	 * @param year             Year
	 * @return Internship DTO
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/external/students/{student_id}/internships",
			"/external/students/{student_id}/internships/" })
	public InternshipDto createInternship(@PathVariable(value = "student_id") String studentID,
			@RequestParam(name = "course_id") String courseID,
			@RequestParam(name = "academic_semester") AcademicSemester academicSemester,
			@RequestParam(name = "year") int year) throws Exception {
		Student student = studentService.findStudentByStudentID(studentID);
		if (student == null) {
			throw new IllegalArgumentException("No student with that id");
		}
		Course course = courseService.findCourseByCourseID(courseID);
		if (course == null) {
			course = courseService.create(new CourseDto(courseID));

		}
		InternshipDto internshipDto = new InternshipDto(year, academicSemester);

		Internship internship = internshipService.create(internshipDto, student, course);

		return internshipService.toDto(internship);

	}

	/**
	 * This method deletes an internship via a DELETE request
	 * 
	 * @param studentID    Student ID
	 * @param internshipID Internship ID
	 */
	@DeleteMapping(value = { "/external/students/{student_id}/{internship_id}", "/external/students/{internship_id}" })
	public void deleteInternship(@PathVariable(value = "student_id") String studentID,
			@PathVariable(value = "internship_id") int internshipID) {
		Student student = studentService.findStudentByStudentID(studentID);
		if (student == null) {
			throw new IllegalArgumentException("No student with that id");
		}
		Internship internship = internshipService.findByIdAndStudent(internshipID, student);
		if (internship == null) {
			throw new IllegalArgumentException("There is no such internship!");
		} else {
			internshipService.deleteInternship(internship);
		}
	}

}
