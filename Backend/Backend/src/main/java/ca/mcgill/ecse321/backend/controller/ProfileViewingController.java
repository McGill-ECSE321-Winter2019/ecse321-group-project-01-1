package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class ProfileViewingController {
    @Autowired
    private BackendApplicationService service;

    @GetMapping(value = {"/students","/students"})
    public StudentDto getStudentProfile(@RequestParam("student") Student student){
        return convertToDto(student);
    }

    private StudentDto convertToDto(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("There is no such Student!");
        }
        StudentDto studentDto = new StudentDto(s.getStudentID(), s.getFirstName(), s.getLastName(), s.getEmail(), s.getPassword());
        studentDto.setInternship(getInternshipDtos(s));
        return studentDto;
    }

    public Set<InternshipDto> getInternshipDtos(Student student){
        Set<Internship> internshipList = student.getInternship();
        if(internshipList == null){
            throw new IllegalArgumentException("There is no such internship!");
        }

        Set<InternshipDto> dtoList = new HashSet<>();
        for(Internship internship : internshipList){
            dtoList.add(convertToDto(internship));
        }
        return dtoList;
    }

    public InternshipDto convertToDto(Internship internship){
        if (internship == null) {
            throw new IllegalArgumentException("There is no such internship!");
        }
        InternshipDto internshipDto = new InternshipDto(internship.getCourse(),internship.getAcademicSemester());
        return internshipDto;
    }
}
