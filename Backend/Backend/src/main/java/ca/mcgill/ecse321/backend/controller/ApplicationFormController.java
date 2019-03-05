package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.service.InternshipService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class ApplicationFormController {
    @Autowired
    private BackendApplicationService service;
    
    @Autowired
    private InternshipService internshipService;
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/post_application")
    public ApplicationFormDto postApplication(
                                              @RequestParam("job_id") String jobID,
                                              @RequestParam("job_description")  String jobDescription,
                                              @RequestParam("employer")  String employer,
                                              @RequestParam("location") String location,
                                              @RequestParam("start_date") Date startDate,
                                              @RequestParam("end_date") Date endDate,
                                              @RequestParam("work_permit") boolean workPermit,
                                              @RequestParam("internship_id") int internshipId
                                ){
    	Internship i =internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
    	ApplicationFormDto applicationFormDto = null;
    	if (i == null) {
    		
    	} else {
            ApplicationForm applicationForm = service.createApplicationForm(jobID, jobDescription, employer, location, startDate,  endDate, workPermit,  service.readInternship(internshipId));
            return convertToDto(applicationForm);
    	}
    	return applicationFormDto;
    	

    }

    public ApplicationFormDto convertToDto(ApplicationForm applicationForm){
        if (applicationForm == null) {
            throw new IllegalArgumentException("There is no such internship!");
        }
        ApplicationFormDto applicationFormDto = new ApplicationFormDto(applicationForm.getId(),
                applicationForm.getJobID(),
                applicationForm.getJobDescription(),
                applicationForm.getEmployer(),
                applicationForm.getLocation(),
                applicationForm.getStartDate(),
                applicationForm.getEndDate(),
                applicationForm.isWorkPermit(),
                applicationForm.getInternship()
                );
        return applicationFormDto;
    }

/*    @GetMapping("/get_application/{id}")
    public ApplicationFormDto getApplication(@RequestParam("internship") InternshipDto internshipDto){
        Internship internship = service.readInternship(internshipDto.getId());
        ApplicationForm applicationForm = internship.getApplicationForm();
        return convertToDto(applicationForm);
    }
*/
    @GetMapping("/get_application/{internship_id}")
    public ApplicationFormDto getApplication(@PathVariable(value="internship_id") int internshipId){
        Internship internship = service.readInternship(internshipId);
        ApplicationForm applicationForm = internship.getApplicationForm();
        return convertToDto(applicationForm);
    }
}
