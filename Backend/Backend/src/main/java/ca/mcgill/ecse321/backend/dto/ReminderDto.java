package ca.mcgill.ecse321.backend.dto;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ReminderDto {
	
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private int id;

	private String message;

	private Date createDateTime;

	private Date readDateTime;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
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

	public Date getReadDateTime() {
		return readDateTime;
	}

	public void setReadDateTime(Date readDateTime) {
		this.readDateTime = readDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

}