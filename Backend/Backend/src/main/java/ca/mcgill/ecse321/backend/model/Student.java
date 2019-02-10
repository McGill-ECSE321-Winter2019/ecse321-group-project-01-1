package ca.mcgill.ecse321.backend.model;
import javax.persistence.OneToMany;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student{
	
	
@OneToMany
private Set<Reminder> reminder;


public Set<Reminder> getReminder() {
   return this.reminder;
}

public void setReminder(Set<Reminder> reminders) {
   this.reminder = reminders;
}

@OneToMany(mappedBy="student")
private Set<ApplicationForm> applicationForms;


public Set<ApplicationForm> getApplicationForms() {
   return this.applicationForms;
}

public void setApplicationForms(Set<ApplicationForm> applicationFormss) {
   this.applicationForms = applicationFormss;
}
	@Id
	private String studentID;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;

}
