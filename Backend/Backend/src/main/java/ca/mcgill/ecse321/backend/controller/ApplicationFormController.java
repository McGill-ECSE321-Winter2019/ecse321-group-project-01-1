package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
public class ApplicationFormController {
    @Autowired
    private BackendApplicationService service;

    @PostMapping("/post_application")
    public ApplicationFormDto postApplication(
                                              @RequestParam("jobid") String jobID,
                                              @RequestParam("jobDescription")  String jobDescription,
                                              @RequestParam("employer")  String employer,
                                              @RequestParam("location") String location,
                                              @RequestParam("startDate") Date startDate,
                                              @RequestParam("endDate") Date endDate,
                                              @RequestParam("workPermit") boolean workPermit,
                                              @RequestParam("internship_id") int internship_id
                                ){
        ApplicationForm applicationForm = service.createApplicationForm(jobID, jobDescription, employer, location, startDate,  endDate, workPermit,  service.readInternship(internship_id));
        return convertToDto(applicationForm);
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
