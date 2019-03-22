package ca.mcgill.ecse321.backend.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.service.ApplicationFormService;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.InternshipService;

@RestController
@CrossOrigin(origins = "*")

public class ApplicationFormController {
    
    @Autowired
    private ApplicationFormService applicationFormService;
    
    @Autowired
    private InternshipService internshipService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/internships/{internship_id}/application_form")
    public ApplicationFormDto postApplication(
                      @RequestParam("job_id") String jobID,
                      @RequestParam("job_description")  String jobDescription,
                      @RequestParam("employer")  String employer,
                      @RequestParam("location") String location,
                      @RequestParam("start_date") Date startDate,
                      @RequestParam("end_date") Date endDate,
                      @RequestParam("work_permit") boolean workPermit,
                      @PathVariable(value="internship_id") int internshipId
        ){
    	Internship i = internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
    	if (i == null) throw new AccessDeniedException("");
    	
        ApplicationFormDto applicationFormDto = new ApplicationFormDto(jobID, jobDescription, employer, location, startDate,  endDate, workPermit);
        ApplicationForm applicationForm = applicationFormService.createApplicationForm(applicationFormDto, i);
        return applicationFormService.toDto(applicationForm);
    }

    @GetMapping("/api/internships/{internship_id}/application_form")
    public ApplicationFormDto getApplication(@PathVariable(value="internship_id") int internshipId){
    	Internship i =internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
    	if (i == null) throw new AccessDeniedException("");
    	
        ApplicationForm applicationForm = i.getApplicationForm();
        return applicationFormService.toDto(applicationForm);
    }

}