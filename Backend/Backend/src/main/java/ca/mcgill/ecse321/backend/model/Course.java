package ca.mcgill.ecse321.backend.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Course {
	
	
	@OneToMany(cascade={CascadeType.ALL})
	private Set<Internship> internship;

	public Set<Internship> getInternship() {
		if (this.internship == null) {
			this.internship = new HashSet<Internship>();
		}
		return this.internship;
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


	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

}
