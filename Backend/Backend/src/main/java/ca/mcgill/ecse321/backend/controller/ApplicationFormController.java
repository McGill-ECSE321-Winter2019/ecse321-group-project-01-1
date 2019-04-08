package ca.mcgill.ecse321.backend.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	/**
	 * This method creates a new application form via a POST request
	 * 
	 * @param jobID          Job ID
	 * @param jobDescription Job Description
	 * @param company        Company
	 * @param employer       Job Employer
	 * @param email          Employer Email
	 * @param location
	 * @param startDate      Starting Date
	 * @param endDate        End Date
	 * @param workPermit     Boolean for whether a job permit is needed
	 * @param internshipId   Internship ID
	 * @return ApplicationForm DTO
	 */

	@PostMapping("/api/internships/{internship_id}/application_form")
	public ApplicationFormDto postApplication(@RequestParam("job_id") String jobID,
			@RequestParam("job_description") String jobDescription, @RequestParam("company") String company,
			@RequestParam("employer") String employer,
			@RequestParam(value = "employer_email", required = false) String email,
			@RequestParam("location") String location, @RequestParam("start_date") Date startDate,
			@RequestParam("end_date") Date endDate, @RequestParam("work_permit") boolean workPermit,

			@PathVariable(value = "internship_id") int internshipId) {
		Internship i = internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
		if (i == null)
			throw new AccessDeniedException("");
		if (i.getApplicationForm() != null)
			throw new IllegalArgumentException("Application form already exists.");
		ApplicationFormDto applicationFormDto = new ApplicationFormDto(jobID, jobDescription, company, employer, email,
				location, startDate, endDate, workPermit);
		ApplicationForm applicationForm = applicationFormService.create(applicationFormDto, i);
		return applicationFormService.toDto(applicationForm);
	}

	/**
	 * This method updates a application form via a PUT request
	 * 
	 * @param jobID          Job ID
	 * @param jobDescription Job Description
	 * @param company        Company
	 * @param employer       Job Employer
	 * @param email          Employer Email
	 * @param location
	 * @param startDate      Starting Date
	 * @param endDate        End Date
	 * @param workPermit     Boolean for whether a job permit is needed
	 * @param internshipId   Internship ID
	 * @return ApplicationForm DTO
	 */
	@PutMapping("/api/internships/{internship_id}/application_form")
	public ApplicationFormDto putApplication(@RequestParam(value = "job_id") String jobID,
			@RequestParam(value = "job_description") String jobDescription,
			@RequestParam(value = "company") String company, @RequestParam(value = "employer") String employer,
			@RequestParam(value = "employer_email", required = false) String email,
			@RequestParam(value = "location") String location, @RequestParam(value = "start_date") Date startDate,
			@RequestParam(value = "end_date") Date endDate, @RequestParam(value = "work_permit") boolean workPermit,
			@PathVariable(value = "internship_id") int internshipId) {
		Internship i = internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
		if (i == null)
			throw new AccessDeniedException("");
		if (i.getApplicationForm() == null)
			throw new IllegalArgumentException("Application form doesn't exist.");
		ApplicationFormDto applicationFormDto = new ApplicationFormDto();
		applicationFormDto.setId(i.getApplicationForm().getId());
		applicationFormDto.setJobID(jobID);
		applicationFormDto.setJobDescription(jobDescription);
		applicationFormDto.setCompany(company);
		applicationFormDto.setEmployer(employer);
		applicationFormDto.setLocation(location);
		applicationFormDto.setStartDate(startDate);
		applicationFormDto.setEndDate(endDate);
		applicationFormDto.setWorkPermit(workPermit);
		applicationFormDto.setEmployerEmail(email);

		ApplicationForm applicationForm = applicationFormService.update(applicationFormDto);
		return applicationFormService.toDto(applicationForm);
	}

	/**
	 * This method gets a application form via a GET request
	 * 
	 * @param internshipId Internship ID
	 * @return ApplicationForm DTO
	 */
	@GetMapping("/api/internships/{internship_id}/application_form")
	public ApplicationFormDto getApplication(@PathVariable(value = "internship_id") int internshipId) {
		Internship i = internshipService.findByIdAndStudent(internshipId, authenticationService.getCurrentStudent());
		if (i == null)
			throw new AccessDeniedException("");

		ApplicationForm applicationForm = i.getApplicationForm();
		return applicationFormService.toDto(applicationForm);
	}

}