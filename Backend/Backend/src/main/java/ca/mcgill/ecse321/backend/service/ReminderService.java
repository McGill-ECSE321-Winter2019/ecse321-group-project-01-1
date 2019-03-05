package ca.mcgill.ecse321.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.mcgill.ecse321.backend.dao.ReminderRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class ReminderService {
	
	@Autowired
	ReminderRepository reminderRepository;
	
	@Transactional
	public Reminder create(@ModelAttribute("reminder") @Valid ReminderDto reminderDto, Student student){
		Reminder reminder = new Reminder();
		reminder.setMessage(reminderDto.getMessage());
		reminder.setStudent(student);
		reminder = reminderRepository.save(reminder);
		return reminder;
	}
	
	@Transactional
	public Reminder findReminderById(int id) {
		return reminderRepository.findReminderById(id);
	}

	
	@Transactional
	public Reminder findReminderByIdAndStudent(int id, Student student) {
		return reminderRepository.findReminderByIdAndStudent(id, student);
	}
	
	@Transactional
	public Reminder findReminderByIdAndStudentStudentID(int id, String studentID) {
		return reminderRepository.findReminderByIdAndStudentStudentID(id, studentID);
	}
	
	@Transactional
	public Reminder update(Reminder reminder){
		reminder = reminderRepository.save(reminder);
		return reminder;
	}
    
	@Transactional
	public List<Reminder> getAll() {
		return toList(reminderRepository.findAll());
	}
	
    public ReminderDto toDto(Reminder reminder) {
		ReminderDto reminderDto = new ReminderDto();
		reminderDto.setCreateDateTime(reminder.getCreateDateTime());
		reminderDto.setMessage(reminder.getMessage());
		reminderDto.setReadDateTime(reminder.getReadDateTime());
		return reminderDto;
    	
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}

