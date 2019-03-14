package ca.mcgill.ecse321.backend.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;


@Entity
public class ApplicationForm{
	
	@Id
	@GeneratedValue(
			strategy= GenerationType.AUTO,
			generator="native"
			)
	@GenericGenerator(
			name = "native",
			strategy = "native"
			)
	private int id; //ID for internal use, to distinguish between different application forms

	private String jobID; //Publicly posted Job ID that can be found with the job description 
	private String jobDescription;

	public ApplicationForm(String jobID, String jobDescription, Internship internship, String employer, String location, Date startDate, Date endDate, boolean workPermit) {
		this.jobID = jobID;
		this.jobDescription = jobDescription;
		this.internship = internship;
		this.employer = employer;
		this.location = location;
		this.startDate = startDate;
		this.endDate = endDate;
		this.workPermit = workPermit;
	}

	public ApplicationForm(){

	}

	@OneToOne(optional=false)
	private Internship internship;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Internship getInternship() {
		return this.internship;
	}

	public void setInternship(Internship internship) {
		this.internship = internship;
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

	public String getJobID() {
		return jobID;
	}

	public void setJobID(String jobID) {
		this.jobID = jobID;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof ApplicationForm)) return false;
		ApplicationForm that = (ApplicationForm) o;
		return getId() == that.getId();
	}

}