package ca.mcgill.ecse321.backend.model;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;


@Entity
public class ApplicationForm{

	@OneToOne(optional=false)
	private Document document;

	public Document getDocument() {
		return this.document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
	@Enumerated(EnumType.STRING)
	private AcademicSemester academicSemester;

	public AcademicSemester getAcademicSemester() {
		return this.academicSemester;
	}

	public void setAcademicSemester(AcademicSemester academicSemester) {
		this.academicSemester = academicSemester;
	}

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne(optional=false)
	private Student student;
	
	private String jobID;
	private String jobDescription;

	private String employer;

	private String location;

	private Date startDate;
	private Date endDate;

	private boolean workPermit;

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
