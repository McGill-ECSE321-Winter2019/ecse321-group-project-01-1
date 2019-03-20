package ca.mcgill.ecse321.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class StudentService {
	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Autowired
	InternshipService internshipService;
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public Student create(@ModelAttribute("student") @Valid StudentDto studentDto) throws RuntimeException {
		
		if (emailExists(studentDto.getEmail())) {   
            throw new IllegalArgumentException(
              "There is already a student with that email address: " + studentDto.getEmail());
        }
		
		if (studentIDExists(studentDto.getStudentID())) {   
            throw new IllegalArgumentException(
              "There is already a student with that student ID: " + studentDto.getStudentID());
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
    
    private boolean studentIDExists(String studentID) {
        Student student = studentRepository.findStudentByStudentID(studentID);
        if (student != null) {
            return true;
        }
        return false;
    }
    
	@Transactional
	public Student update(Student student){
		studentRepository.save(student);
		return student;
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
		return studentDto;
    	
    }
    
    public StudentDto deepToDto(Student student) {
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentID(student.getStudentID());
		studentDto.setFirstName(student.getFirstName());
		studentDto.setLastName(student.getLastName());
		studentDto.setEmail(student.getEmail());
		HashSet<InternshipDto> internshipDtos = new HashSet<>();
		for (Internship internship : student.getInternship()) {
			internshipDtos.add(internshipService.deepToDto(internship));
		}
		studentDto.setInternship(internshipDtos);
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

