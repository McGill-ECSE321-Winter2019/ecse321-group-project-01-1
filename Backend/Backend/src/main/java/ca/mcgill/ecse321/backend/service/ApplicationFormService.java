package ca.mcgill.ecse321.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.ApplicationFormDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class ApplicationFormService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ApplicationFormRepository applicationFormRepository;
	
	@Transactional
	public ApplicationForm createApplicationForm(
			@ModelAttribute("applicationForm") @Valid ApplicationFormDto applicationFormDto, 
			Internship internship
			){
		
		ApplicationForm applicationForm = new ApplicationForm();

		applicationForm.setJobID(applicationFormDto.getJobID());
		applicationForm.setEmployer(applicationFormDto.getEmployer());
		applicationForm.setEndDate(applicationFormDto.getEndDate());
		applicationForm.setStartDate(applicationFormDto.getStartDate());
		applicationForm.setWorkPermit(applicationFormDto.isWorkPermit());
		applicationForm.setLocation(applicationFormDto.getLocation());
		applicationForm.setJobDescription(applicationFormDto.getJobDescription());
		applicationForm.setInternship(internship);
		
		applicationForm = applicationFormRepository.save(applicationForm);

		return applicationForm;
	}

	@Transactional
	public ApplicationForm update(ApplicationForm form){
		return applicationFormRepository.save(form);
	}
	
    
	@Transactional
	public List<ApplicationForm> getAll() {
		return toList(applicationFormRepository.findAll());
	}
	
	@Transactional
	public ApplicationForm findApplicationFormById(int id) {
		return applicationFormRepository.findApplicationFormById(id);
	}
	
    public ApplicationFormDto toDto(ApplicationForm applicationForm){
        ApplicationFormDto applicationFormDto = new ApplicationFormDto(
        	applicationForm.getId(),
            applicationForm.getJobID(),
            applicationForm.getJobDescription(),
            applicationForm.getEmployer(),
            applicationForm.getLocation(),
            applicationForm.getStartDate(),
            applicationForm.getEndDate(),
            applicationForm.isWorkPermit()
            );
        return applicationFormDto;
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}

