package ca.mcgill.ecse321.backend.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Student;

@Service
public class StudentService {
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
		S = studentRepository.save(S);
		return S;
	}
	
    private boolean emailExists(String email) {
        Student student = studentRepository.findStudentByEmail(email);
        if (student != null) {
            return true;
        }
        return false;
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
    
}

