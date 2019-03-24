package ca.mcgill.ecse321.backend.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;


@Entity
public class Internship{

	public Internship() {
		
	}
	
    public Internship(int year, AcademicSemester academicSemester, Course course, Student student) {
    	this.year = year;
        this.academicSemester = academicSemester;
        this.course = course;
        this.student = student;
    }
	
	@ManyToOne(optional=false)
	private Course course;

	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
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
	@OneToOne(mappedBy="internship", cascade={CascadeType.ALL})
	private ApplicationForm applicationForm;

	private int year;
	public ApplicationForm getApplicationForm() {
		return this.applicationForm;
	}

	public void setApplicationForm(ApplicationForm applicationForm) {
		this.applicationForm = applicationForm;
	}



	@OneToMany(mappedBy="internship", cascade={CascadeType.ALL})
	private Set<Document> document = new HashSet<Document>();

	public Set<Document> getDocument() {
		return this.document;
	}

	public void setDocument(Set<Document> documents) {
		this.document = documents;
	}

	@Enumerated(EnumType.STRING)
	private AcademicSemester academicSemester;

	public AcademicSemester getAcademicSemester() {
		return this.academicSemester;
	}

	public void setAcademicSemester(AcademicSemester academicSemester) {
		this.academicSemester = academicSemester;
	}

	@ManyToOne(optional=false)
	private Student student;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Internship)) return false;
		Internship that = (Internship) o;
		return id == that.id;
	}

}
