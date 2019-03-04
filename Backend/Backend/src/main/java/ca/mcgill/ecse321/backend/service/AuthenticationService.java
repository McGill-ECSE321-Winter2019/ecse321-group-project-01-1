package ca.mcgill.ecse321.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.Student;



@Service
public class AuthenticationService {
	
	@Autowired 
	private StudentRepository studentRepository;
	
    public Student getCurrentStudent() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            return studentRepository.findStudentByEmail(((User)user).getUsername());
        }

        return null;
    }
}
