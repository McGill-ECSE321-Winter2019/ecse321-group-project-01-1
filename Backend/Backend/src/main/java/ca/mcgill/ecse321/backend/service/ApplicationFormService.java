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
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class ApplicationFormService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	ApplicationFormRepository applicationFormRepository;
	
	@Transactional
	public ApplicationForm createApplicationForm(@ModelAttribute("applicationForm") @Valid ApplicationFormDto applicationFormDto) throws Exception {
		
		return new ApplicationForm();
	}
	
    
	@Transactional
	public List<ApplicationForm> getAll() {
		return toList(applicationFormRepository.findAll());
	}
	
    public StudentDto toDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentID(student.getStudentID());
		studentDto.setFirstName(student.getFirstName());
		studentDto.setLastName(student.getLastName());
		studentDto.setEmail(student.getEmail());
		studentDto.setPassword(student.getPassword());
		return studentDto;
    	
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}

