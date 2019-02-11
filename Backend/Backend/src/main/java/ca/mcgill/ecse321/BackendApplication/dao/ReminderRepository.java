package ca.mcgill.ecse321.BackendApplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.Reminder;

public interface ReminderRepository extends CrudRepository<Reminder, Integer>{

	Reminder findReminderById(int id);

}
