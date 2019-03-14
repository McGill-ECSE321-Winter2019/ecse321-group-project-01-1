package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping(value = { "/external/students", "/external/students/" })
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
