package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;
import org.springframework.data.repository.CrudRepository;

public interface ReminderRepository extends CrudRepository<Reminder, Integer>{

	Reminder findReminderById(int id);
	Reminder findReminderByIdAndStudent(int id, Student student);
	Reminder findReminderByIdAndStudentStudentID(int id, String studentID);


}
