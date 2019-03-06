package ca.mcgill.ecse321.backend.dto;

import ca.mcgill.ecse321.backend.model.Internship;

import java.sql.Date;

public class ApplicationFormDto {

    private int id;

    private String jobID;
    private String jobDescription;

    private String employer;

    private String location;

    private Date startDate;
    private Date endDate;

    private boolean workPermit;

    private Internship internship;

    public ApplicationFormDto(int id, String jobID, String jobDescription, String employer, String location, Date startDate, Date endDate, boolean workPermit, Internship internship) {
        this.id = id;
        this.jobID = jobID;
        this.jobDescription = jobDescription;
        this.employer = employer;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workPermit = workPermit;
        this.internship = internship;
    }

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

    public String getJobID() {
        return jobID;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

}
