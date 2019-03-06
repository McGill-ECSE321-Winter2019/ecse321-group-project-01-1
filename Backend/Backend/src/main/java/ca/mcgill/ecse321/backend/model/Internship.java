package ca.mcgill.ecse321.backend.model;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;


@Entity
public class Internship{

	public Internship(Course course, AcademicSemester academicSemester, Student student) {
		this.course = course;
		this.academicSemester = academicSemester;
		this.student = student;
	}

	public Internship(){

	}

	public Internship(Course course){
		this.course = course;
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

	public ApplicationForm getApplicationForm() {
		return this.applicationForm;
	}

	public void setApplicationForm(ApplicationForm applicationForms) {
		this.applicationForm = applicationForms;
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Internship)) return false;
		Internship that = (Internship) o;
		return id == that.id;
	}

}