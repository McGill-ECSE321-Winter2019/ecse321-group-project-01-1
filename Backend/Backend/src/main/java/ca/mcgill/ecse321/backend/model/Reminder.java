package ca.mcgill.ecse321.backend.model;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;

@Entity
public class Reminder{

	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
			)
	@GenericGenerator(
			name = "native",
			strategy = "native"
			)
	private int id;

	private String message;

	@CreationTimestamp
	private Time createDateTime;

	private Time readDateTime;

	@ManyToOne(optional=false)
	private Student student;

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
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
