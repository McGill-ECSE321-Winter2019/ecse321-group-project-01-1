package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationService {
	
	@Autowired 
	private StudentRepository studentRepository;
	
	@Transactional
    public Student getCurrentStudent() {
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user instanceof User) {
            return studentRepository.findStudentByEmail(((User)user).getUsername());
        }
        throw new AccessDeniedException("");
    }
}
