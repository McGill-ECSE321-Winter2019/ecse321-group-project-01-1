package ca.mcgill.ecse321.backend.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentDto{
	
	public StudentDto(String studentID, String firstName, String lastName, String email, String password) {
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;

	}
	
	public StudentDto() {
		
	}
	
    @NotNull
    @NotEmpty
	private String studentID;
	
    @NotNull
    @NotEmpty
	private String firstName;

    @NotNull
    @NotEmpty
	private String lastName;

    @NotNull
    @NotEmpty
    @Email
	private String email;

    @NotNull
    @NotEmpty
	private String password;

	private Set<ReminderDto> reminder;
	
	public String getStudentID() {
		return studentID;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<ReminderDto> getReminder() {
		return this.reminder;
	}

	public void setReminder(Set<ReminderDto> reminders) {
		this.reminder = reminders;
	}
	
	private InternshipDto internship;

	public InternshipDto getInternship() {
		return this.internship;
	}

	public void setInternship(InternshipDto internship) {
		this.internship = internship;
	}
}
