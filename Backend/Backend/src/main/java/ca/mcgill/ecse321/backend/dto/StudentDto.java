package ca.mcgill.ecse321.backend.dto;

import ca.mcgill.ecse321.backend.model.Internship;

import java.util.Collections;
import java.util.List;

public class StudentDto {

    private String studentID;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Internship internship;

    public StudentDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName =lastName;
    }

    public Internship getInternship() {
        return internship;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public StudentDto() {

    }


}
