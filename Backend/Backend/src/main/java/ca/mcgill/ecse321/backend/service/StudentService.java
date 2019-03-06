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

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class StudentService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Student createStudent(@ModelAttribute("student") @Valid StudentDto studentDto) throws Exception {
		
		if (emailExists(studentDto.getEmail())) {   
            throw new Exception(
              "There is already a student with that email address: " + studentDto.getEmail());
        }
		
		Student S = new Student();
		S.setStudentID(studentDto.getStudentID());
		S.setFirstName(studentDto.getFirstName());
		S.setLastName(studentDto.getLastName());
		S.setEmail(studentDto.getEmail());
		S.setPassword(passwordEncoder.encode(studentDto.getPassword()));
		entityManager.persist(S);

		return S;
	}
	
	@Transactional
	public Student findStudentByStudentID(String studentID) {
		return studentRepository.findStudentByStudentID(studentID);
	}
	
	@Transactional
	public Student findStudentByEmail(String email) {
		return studentRepository.findStudentByEmail(email);
	}
	
	@Transactional
	public Student findStudentById(String studentID) {
		return studentRepository.findStudentByStudentID(studentID);
	}
	
    private boolean emailExists(String email) {
        Student student = studentRepository.findStudentByEmail(email);
        if (student != null) {
            return true;
        }
        return false;
    }
    
	@Transactional
	public List<Student> getAll() {
		return toList(studentRepository.findAll());
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

