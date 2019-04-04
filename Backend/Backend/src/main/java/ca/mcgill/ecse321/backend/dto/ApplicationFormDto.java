package ca.mcgill.ecse321.backend.dto;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ApplicationFormDto {

    private int id;

    private String jobID;
    private String jobDescription;

    @NotNull
    @NotEmpty
    private String employer;

    @NotNull
    @NotEmpty
    private String location;

    private String employerEmail;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private boolean workPermit;

    @JsonProperty(access= JsonProperty.Access.WRITE_ONLY)
    private InternshipDto internship;

    public ApplicationFormDto() {
    	
    }
    
    public ApplicationFormDto(int id, String jobID, String jobDescription, String employer, String location, Date startDate, Date endDate, boolean workPermit, String employerEmail) {
        this.id = id;
        this.jobID = jobID;
        this.jobDescription = jobDescription;
        this.employer = employer;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workPermit = workPermit;
        this.employerEmail = employerEmail;
    }
    
    public ApplicationFormDto(String jobID, String jobDescription, String employer, String location, Date startDate, Date endDate, boolean workPermit,String employerEmail) {
        this.jobID = jobID;
        this.jobDescription = jobDescription;
        this.employer = employer;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.workPermit = workPermit;
        this.employerEmail = employerEmail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployerEmail() {
        return employerEmail;
    }

    public void setEmployerEmail(String employerEmail) {
        this.employerEmail = employerEmail;
    }

    public InternshipDto getInternship() {
        return this.internship;
    }

    public void setInternship(InternshipDto internship) {
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
