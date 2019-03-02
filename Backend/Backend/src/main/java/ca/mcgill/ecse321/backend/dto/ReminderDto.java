package ca.mcgill.ecse321.backend.dto;

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
