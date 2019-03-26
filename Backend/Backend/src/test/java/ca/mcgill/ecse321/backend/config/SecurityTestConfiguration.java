package ca.mcgill.ecse321.backend.config;

import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.model.Student;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

@TestConfiguration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityTestConfiguration {

//	@Mock
//	private StudentDetailsService studentDetailsService;

	@Mock
	private StudentRepository studentRepository;

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private static final String STUDENT_EMAIL = "last.first@mail.mcgill.ca";
	private static final String STUDENT_ID = "260123456";

	@Bean
	@Primary
	public UserDetailsService userDetailsService() {

		MockitoAnnotations.initMocks(this);

		return new UserDetailsService() {
			
			private Student createMock() {
				Student student = new Student();
				student.setEmail(STUDENT_EMAIL);
				student.setFirstName("Firstname");
				student.setStudentID(STUDENT_ID);
				student.setLastName("Lastname");
				student.setPassword(passwordEncoder.encode("123456"));
				return student;
			}
			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
				Student student = null;
				
				if (email.equals(STUDENT_EMAIL)) {
					student = createMock();
				}
				
				// TODO Auto-generated method stub
				if (student == null) throw new UsernameNotFoundException(email);
				
				return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), getAuthorities(new ArrayList<String>()));
			}

			
		    private List<GrantedAuthority> getAuthorities (List<String> roles) {
		        List<GrantedAuthority> authorities = new ArrayList<>();
		        for (String role : roles) {
		            authorities.add(new SimpleGrantedAuthority(role));
		        }
		        return authorities;
		    }
        };
		//        UserDetails userDetails1 = new User("test1@test.com", "Test12345");
		//        UserDetails userDetails2 = new User("test0@test.com", "Test12345");

	}
}