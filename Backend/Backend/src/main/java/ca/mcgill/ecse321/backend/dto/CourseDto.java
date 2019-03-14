package ca.mcgill.ecse321.backend.dto;

import java.util.HashSet;
import java.util.Set;

public class CourseDto {
	
	public CourseDto(String courseID) {
		this.courseID = courseID;
	}
	public CourseDto() {
	}
	
	private Set<InternshipDto> internship = new HashSet<InternshipDto>();

	public Set<InternshipDto> getInternship() {
		if (this.internship == null) {
			this.internship = new HashSet<InternshipDto>();
		}
		return this.internship;
	}

	public Set<InternshipDto> setInternship(InternshipDto new_internship){
		internship.add(new_internship);
		return internship;
	}

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
}