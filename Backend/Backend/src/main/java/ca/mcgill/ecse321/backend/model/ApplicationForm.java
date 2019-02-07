package ca.mcgill.ecse321.backend.model;
import javax.persistence.OneToOne;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;


@Entity
public class ApplicationForm{
private Document document;

@ManyToOne(optional=false)
public Document getDocument() {
   return this.document;
}

public void setDocument(Document document) {
   this.document = document;
}

private AcademicSemester academicSemester;

public AcademicSemester getAcademicSemester() {
   return this.academicSemester;
}

public void setAcademicSemester(AcademicSemester academicSemester) {
   this.academicSemester = academicSemester;
}

private Student student;
private String jobID;
private String jobDescription;

private String employer;

private String location;

private Date startDate;
private Date endDate;

private boolean workPermit;

@ManyToOne(optional=false)
public Student getStudent() {
   return this.student;
}

public void setStudent(Student student) {
   this.student = student;
}

}
