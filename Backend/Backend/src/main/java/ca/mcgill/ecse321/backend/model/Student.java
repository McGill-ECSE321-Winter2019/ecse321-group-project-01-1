package ca.mcgill.ecse321.backend.model;
import javax.persistence.OneToMany;
import java.util.Set;

import javax.persistence.Entity;

@Entity
public class Student{
private Set<Reminder> reminder;

@OneToMany
public Set<Reminder> getReminder() {
   return this.reminder;
}

public void setReminder(Set<Reminder> reminders) {
   this.reminder = reminders;
}

private Set<ApplicationForm> applicationForms;

@OneToMany(mappedBy="student")
public Set<ApplicationForm> getApplicationForms() {
   return this.applicationForms;
}

public void setApplicationForms(Set<ApplicationForm> applicationFormss) {
   this.applicationForms = applicationFormss;
}

	private String studentID;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;

}
