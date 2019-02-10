package ca.mcgill.ecse321.backend.model;
import javax.persistence.OneToMany;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student{

	@OneToMany
	private Set<Reminder> reminder;

	@OneToMany(mappedBy="student")
	private Set<ApplicationForm> applicationForms;


	@Id
	private String studentID;

	private String firstName;

	private String lastName;

	private String email;

	private String password;


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

	public Set<ApplicationForm> getApplicationForms() {
		return this.applicationForms;
	}

	public void setApplicationForms(Set<ApplicationForm> applicationFormss) {
		this.applicationForms = applicationFormss;
	}

	public Set<Reminder> getReminder() {
		return this.reminder;
	}

	public void setReminder(Set<Reminder> reminders) {
		this.reminder = reminders;
	}
}
