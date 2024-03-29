package ca.mcgill.ecse321.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


//Represents the course that an internship is associated with (e.g. FACC250)
@Entity
public class Course {

	public Course(Set<Internship> internship, String courseID) {
		this.internship = internship;
		this.courseID = courseID;
	}

	public Course(){

	}
	@OneToMany(fetch= FetchType.EAGER, cascade={CascadeType.ALL})
	private Set<Internship> internship = new HashSet<Internship>();

	public Set<Internship> getInternship() {
		if (this.internship == null) {
			this.internship = new HashSet<Internship>();
		}
		return this.internship;
	}

	public Set<Internship> setInternship(Internship new_internship){
		internship.add(new_internship);
		return internship;
	}

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
		if (!(o instanceof Course)) return false;
		Course course = (Course) o;
		return getId() == course.getId();
	}
}