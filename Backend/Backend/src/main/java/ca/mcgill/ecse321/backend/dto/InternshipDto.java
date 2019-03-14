package ca.mcgill.ecse321.backend.dto;

import ca.mcgill.ecse321.backend.model.AcademicSemester;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Student;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InternshipDto {

    public InternshipDto(){

    }

    public InternshipDto(AcademicSemester academicSemesterm) {
        this.academicSemester = academicSemester;
    }

    private int id;

    private CourseDto course;

    private AcademicSemester academicSemester;
    
    private boolean[] progress;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CourseDto getCourse() {
        return this.course;
    }

    public void setCourse(CourseDto course) {
        this.course = course;
    }

    private ApplicationForm applicationForm;

    public ApplicationForm getApplicationForm() {
        return this.applicationForm;
    }

    public void setApplicationForm(ApplicationForm applicationForms) {
        this.applicationForm = applicationForms;
    }

    private Set<Document> document = new HashSet<Document>();

    public Set<Document> getDocument() {
        return this.document;
    }

    public void setDocument(Set<Document> documents) {
        this.document = documents;
    }

    public AcademicSemester getAcademicSemester() {
        return this.academicSemester;
    }

    public void setAcademicSemester(AcademicSemester academicSemester) {
        this.academicSemester = academicSemester;
    }

    private Student student;

    public Student getStudent() {
        return this.student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

	public boolean[] getProgress() {
		return progress;
	}

	public void setProgress(boolean[] progress) {
		this.progress = progress;
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InternshipDto)) return false;
        InternshipDto that = (InternshipDto) o;
        return getId() == that.getId() &&
                getAcademicSemester() == that.getAcademicSemester() &&
                Arrays.equals(getProgress(), that.getProgress());
    }
}
