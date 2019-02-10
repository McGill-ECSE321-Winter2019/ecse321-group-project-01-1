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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getEmployer() {
		return employer;
	}

	public void setEmployer(String employer) {
		this.employer = employer;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isWorkPermit() {
		return workPermit;
	}

	public void setWorkPermit(boolean workPermit) {
		this.workPermit = workPermit;
	}

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

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

}
