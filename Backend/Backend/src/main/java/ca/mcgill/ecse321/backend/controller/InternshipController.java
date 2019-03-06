package ca.mcgill.ecse321.backend.controller;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.InternshipService;


@CrossOrigin(origins = "*")
@RestController
public class InternshipController {
	
    @Autowired
    private InternshipService internshipService;
    
    @Autowired
    private AuthenticationService authenticationService;
	
	@GetMapping(value = { "/api/internships/{internship_id}", "/api/internships/{internship_id}" })
	public InternshipDto getInternship(@PathVariable(value="internship_id") int internshipId) {
		Student student = authenticationService.getCurrentStudent();
		Internship i = internshipService.findByIdAndStudent(internshipId, student);
		return internshipService.toDto(i);
	}

}
