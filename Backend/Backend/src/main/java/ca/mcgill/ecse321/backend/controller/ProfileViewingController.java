package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class ProfileViewingController {
    @Autowired
    private InternshipService internshipService;
    
    @Autowired
    private StudentService studentService;

    @Autowired
    private AuthenticationService authenticationService;
    
    @GetMapping(value = {"/api/profile", "/api/profile/"})
    public StudentDto getStudentProfile(){
    	Student student = authenticationService.getCurrentStudent();
        StudentDto studentDto = studentService.toDto(student);
        return studentDto;
    }

}
