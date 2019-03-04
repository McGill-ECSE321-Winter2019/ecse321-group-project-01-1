package ca.mcgill.ecse321.backend.service;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.Student;

@Service
public class StudentDetailsService implements UserDetailsService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		
		Student student = studentRepository.findStudentByEmail(email);
		// TODO Auto-generated method stub
		if (student == null) throw new UsernameNotFoundException(email);
		
		return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), getAuthorities(new ArrayList<String>()));
	}

	
    private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
