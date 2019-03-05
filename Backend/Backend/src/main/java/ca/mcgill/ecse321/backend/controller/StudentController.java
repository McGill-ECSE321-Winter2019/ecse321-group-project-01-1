package ca.mcgill.ecse321.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping(value = { "/external/student", "/external/student/" })
	public StudentDto register(@RequestParam(name = "student_id") String studentID,
			@RequestParam(name = "first_name") String firstName,
			@RequestParam(name = "last_name") String lastName,
			@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password
			) throws Exception {
		
		StudentDto studentDto = new StudentDto(studentID, firstName, lastName, email, password);
		
		Student student = studentService.create(studentDto);
		return studentService.toDto(student);
		
	}

}
