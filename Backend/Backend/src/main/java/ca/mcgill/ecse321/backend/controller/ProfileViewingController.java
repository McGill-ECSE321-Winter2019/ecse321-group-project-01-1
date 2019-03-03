package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.BackendApplication;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.dto.UploadFileResponse;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.service.StorageService;
import ca.mcgill.ecse321.backend.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Email;
import java.util.ArrayList;
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
        return new StudentDto(s.getStudentID(), s.getFirstName(), s.getLastName(), s.getEmail(), getInternshipDtos(s));
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
