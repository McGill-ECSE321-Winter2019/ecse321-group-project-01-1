package ca.mcgill.ecse321.backend.dto;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StudentDto{

    public StudentDto(String studentID, String firstName, String lastName, @Email String email, String password) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

	public StudentDto(String studentID, String firstName, String lastName, @Email String email, String password ,Set<InternshipDto> internships) {
		this.studentID = studentID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
        this.password = password;
		this.internship = internships;
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
	
  private Set<InternshipDto> internship;

  public Set<InternshipDto> getInternship() {
      return this.internship;
  }

  public void setInternship(Set<InternshipDto> internship){
      this.internship = internship;
  }
}
