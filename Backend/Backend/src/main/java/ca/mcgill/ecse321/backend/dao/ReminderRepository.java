package ca.mcgill.ecse321.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

public interface ReminderRepository extends CrudRepository<Reminder, Integer>{

	Reminder findReminderById(int id);
	Reminder findReminderByIdAndStudent(int id, Student student);
	Reminder findReminderByIdAndStudentStudentID(int id, String studentID);


}
