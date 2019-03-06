package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StudentService;
import ca.mcgill.ecse321.backend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class ProfileViewingController {
    @Autowired
    private InternshipService internshipService;
    
    @Autowired
    private StudentService studentService;


    @GetMapping(value = {"/api/profile", "/api/profile/"})
    public StudentDto getStudentProfile(@RequestParam("student") Student student){
        return studentService.toDto(student);
    }

    public Set<InternshipDto> getInternshipDtos(Student student){
        Set<Internship> internshipList = student.getInternship();
        if(internshipList == null){
            throw new IllegalArgumentException("There is no such internship!");
        }

        Set<InternshipDto> dtoList = new HashSet<>();
        for(Internship internship : internshipList){
            dtoList.add(internshipService.toDto(internship));
        }
        return dtoList;
    }


}
