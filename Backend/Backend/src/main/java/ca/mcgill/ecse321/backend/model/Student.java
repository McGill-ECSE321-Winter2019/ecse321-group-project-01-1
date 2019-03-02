package ca.mcgill.ecse321.backend.model;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student{

	@Id
	private String studentID;

	private String firstName;

	private String lastName;

	private String email;

	private String password;

	@OneToMany(cascade={CascadeType.ALL}, mappedBy="student")
	private Set<Reminder> reminder;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Reminder> getReminder() {
		return this.reminder;
	}

	public void setReminder(Set<Reminder> reminders) {
		this.reminder = reminders;
	}
	
	@OneToMany(mappedBy="student", cascade={CascadeType.ALL})
	private Set<Internship> internship;

	public Set<Internship> getInternship() {
		return this.internship;
	}

	public void setInternship(Set<Internship> internships) {
		this.internship = internships;
	}

}