package ca.mcgill.ecse321.backend.dto;
import java.sql.Time;

public class ReminderDto {
	
	private int id;

	private String message;

	private Time createDateTime;

	private Time readDateTime;

	private StudentDto student;

	public ReminderDto () {
	}
	
	public ReminderDto (String message) {
		this.message = message;
	}
	
	public StudentDto getStudent() {
		return this.student;
	}

	public void setStudent(StudentDto student) {
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