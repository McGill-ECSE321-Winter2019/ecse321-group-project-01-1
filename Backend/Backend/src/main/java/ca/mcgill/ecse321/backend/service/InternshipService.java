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
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class InternshipService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	InternshipRepository internshipRepository;
	
	@Transactional
	public Internship createInternship(@ModelAttribute("internship") @Valid InternshipDto internshipDto) throws Exception {
		Internship internship = new Internship();
    	internship.setAcademicSemester(internshipDto.getAcademicSemester());
    	internship.setApplicationForm(internshipDto.getApplicationForm());
    	internship.setCourse(internshipDto.getCourse());
    	internship.setDocument(internshipDto.getDocument());
    	internship.setStudent(internshipDto.getStudent());
    	return internshipRepository.save(internship);
	}

	@Transactional
	public Internship findByIdAndStudentStudentID(int id, String studentID) {
		return internshipRepository.findByIdAndStudentStudentID(id, studentID);
	}
	
	@Transactional
	public Internship findByIdAndStudent(int id, Student student) {
		return internshipRepository.findByIdAndStudent(id, student);
	}
	
    public InternshipDto toDto(Internship internship) {
    	InternshipDto internshipDto = new InternshipDto();
    	internshipDto.setAcademicSemester(internship.getAcademicSemester());
    	internshipDto.setApplicationForm(internship.getApplicationForm());
    	internshipDto.setCourse(internship.getCourse());
    	internshipDto.setDocument(internship.getDocument());
    	internshipDto.setStudent(internship.getStudent());
    	internshipDto.setId(internship.getId());
    	return internshipDto;
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	@Transactional
	public List<Internship> getAll() {
		return toList(internshipRepository.findAll());
	}
    
}

