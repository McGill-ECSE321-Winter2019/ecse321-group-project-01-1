package ca.mcgill.ecse321.backend.dto;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CourseDto {
	
	public CourseDto(String courseID) {
		this.courseID = courseID;
	}
	public CourseDto() {
	}
	
    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
	private Set<InternshipDto> internship;

	private int id;

	private String courseID;
  
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CourseDto)) return false;
		CourseDto courseDto = (CourseDto) o;
		return getId() == courseDto.getId() &&
				getCourseID().equals(courseDto.getCourseID());
	}
	

	public Set<InternshipDto> getInternship() {
		return internship;
	}
	
	public void setInternship(Set<InternshipDto> internship) {
		this.internship = internship;
	}
}