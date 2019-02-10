package ca.mcgill.ecse321.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;

@Entity
public class Reminder{
    @Id
    @GeneratedValue
    private int id;
	
	private String message;
	
	@CreationTimestamp
	private Time createDateTime;
	
	private Time readDateTime;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Time getReadDateTime() {
		return readDateTime;
	}

	public void setReadDateTime(Time readDateTime) {
		this.readDateTime = readDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Time createDateTime) {
		this.createDateTime = createDateTime;
	}
	
}
