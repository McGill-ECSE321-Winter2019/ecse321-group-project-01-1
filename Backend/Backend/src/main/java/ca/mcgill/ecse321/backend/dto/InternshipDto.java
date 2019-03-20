package ca.mcgill.ecse321.backend.dto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ca.mcgill.ecse321.backend.model.AcademicSemester;

@JsonInclude(Include.NON_NULL)
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

    private ApplicationFormDto applicationForm;

    public ApplicationFormDto getApplicationForm() {
        return this.applicationForm;
    }

    public void setApplicationForm(ApplicationFormDto applicationForms) {
        this.applicationForm = applicationForms;
    }

    private Set<DocumentDto> document = new HashSet<DocumentDto>();

    public Set<DocumentDto> getDocument() {
        return this.document;
    }

    public void setDocument(Set<DocumentDto> documents) {
        this.document = documents;
    }

    public AcademicSemester getAcademicSemester() {
        return this.academicSemester;
    }

    public void setAcademicSemester(AcademicSemester academicSemester) {
        this.academicSemester = academicSemester;
    }

    private StudentDto student;

    public StudentDto getStudent() {
        return this.student;
    }

    public void setStudent(StudentDto student) {
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
