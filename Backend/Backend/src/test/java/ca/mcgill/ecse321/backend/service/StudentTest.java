package ca.mcgill.ecse321.backend.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.dao.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Autowired
    private BackendApplicationService service;

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private ApplicationFormRepository applicationFormRepository;
    @Autowired
    private ReminderRepository reminderRepository;



    @Before
    public void clear(){
        studentRepository.deleteAll();
        documentRepository.deleteAll();
        applicationFormRepository.deleteAll();
        reminderRepository.deleteAll();
    }

    @Test
    public void testStudentCreation() {
        //assert no student in repository
        assertEquals(0, service.getAllStudents().size());

        //create new student
        String id = "000000000";
        String fname = "John";
        String lname = "Doe";
        String email = "john.doe@mail.mcgill.ca";
        String pass = "123456";

        service.createStudent(id, fname, lname, email, pass);

        //assert only 1 student
        List<Student> allStudents = service.getAllStudents();
        assertEquals(1, allStudents.size());

        //read student
        Student test = service.readStudent(id);

        assertEquals(test.getFirstName(),fname);
        assertEquals(test.getLastName(),lname);
        assertEquals(test.getStudentID(),id);
        assertEquals(test.getEmail(),email);
        assertEquals(test.getPassword(),pass);

    }

    @Test
    public void testStudentUpdate(){
        assertEquals(0, service.getAllStudents().size());
        //create new student
        String id = "000000000";
        String fname = "John";
        String lname = "Doe";
        String email = "john.doe@mail.mcgill.ca";
        String pass = "123456";

        service.createStudent(id, fname, lname, email, pass);

        Student updatedStudent = service.readStudent("000000000");

        String newFname = "Jane";
        String newLname = "Jones";
        String newEmail = "jane.Jones@mail.mcgill.ca";
        String newPass = "password";

        updatedStudent.setEmail(newEmail);
        updatedStudent.setFirstName(newFname);
        updatedStudent.setLastName(newLname);
        updatedStudent.setPassword(newPass);

        service.updateStudent(updatedStudent);

        // makes sure there is only 1 left
        assertEquals(1, service.getAllStudents().size());

        Student fetchedStudent = service.readStudent(id);

        assertEquals(fetchedStudent.getFirstName(),newFname);
        assertEquals(fetchedStudent.getLastName(),newLname);
        assertEquals(fetchedStudent.getEmail(),newEmail);
        assertEquals(fetchedStudent.getPassword(),newPass);
    }
}
