package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.BackendApplication;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.dto.UploadFileResponse;
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
        StudentDto personDto = new StudentDto(s.getFirstName(),s.getLastName());
        return personDto;
    }


}
