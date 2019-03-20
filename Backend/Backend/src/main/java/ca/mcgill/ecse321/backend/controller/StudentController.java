package ca.mcgill.ecse321.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
    @GetMapping(value = {"/external/students/{student_id}", "/external/students/{student_id}/"})
    public StudentDto getStudentProfileExternal(@PathVariable(value = "student_id") String studentID){
    	Student student = studentService.findStudentByStudentID(studentID);
        if(student == null){
            throw new IllegalArgumentException("There is no such student!");
        }
        StudentDto studentDto = studentService.deepToDto(student);
        return studentDto;
    }
    
    @GetMapping(value = {"/external/students}", "/external/students}/"})
    public List<StudentDto> getAllStudentsExternal(){
    	List<Student> studentsList = studentService.getAll();
        ArrayList<StudentDto> studentDtos = new ArrayList<>();
        for (Student student: studentsList) {
        	studentDtos.add(studentService.toDto(student));
        }
        return studentDtos;
    }
	
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
