package ca.mcgill.ecse321.backend.dto;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import java.sql.Time;

public class ReminderDto {
	
	private String message;
	private Time createDateTime;
	
	public ReminderDto (String message, Time createDateTime) {
		this.message = message;
		this.createDateTime = createDateTime;
		
	}
	
	public String getMessage() {
		return message;
	}
	
	public Time getCreateDateTime() {
		return createDateTime;
	}


}
