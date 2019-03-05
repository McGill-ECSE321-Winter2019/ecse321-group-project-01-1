package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.ApplicationFormService;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.service.InternshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class ApplicationFormController {
    @Autowired
    private BackendApplicationService service;
    
    @Autowired
    private ApplicationFormService applicationFormService;
    
    @Autowired
    private InternshipService internshipService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/internship/{internship_id}/application_form")
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

    @GetMapping("/api/internship/{internship_id}/application_form")
    public ApplicationFormDto getApplication(@PathVariable(value="internship_id") int internshipId){
    	Internship i =internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
    	if (i == null) throw new AccessDeniedException("");
    	
        ApplicationForm applicationForm = i.getApplicationForm();
        return applicationFormService.toDto(applicationForm);
    }

}
