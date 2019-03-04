package ca.mcgill.ecse321.backend.dto;

import ca.mcgill.ecse321.backend.model.*;

import java.util.HashSet;
import java.util.Set;

public class InternshipDto {

    public InternshipDto(){

    }

    public InternshipDto(Course course, AcademicSemester academicSemester) {
        this.course = course;
        this.academicSemester = academicSemester;
    }
    private int id;

    private Course course;

    private AcademicSemester academicSemester;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
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
}
