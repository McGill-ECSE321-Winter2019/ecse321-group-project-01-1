package ca.mcgill.ecse321.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class ReminderController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	BackendApplicationService service = new BackendApplicationService();

	@PostMapping(value = { "/reminders", "/reminders/" })
	public ReminderDto createReminder(@RequestParam(name = "message") String message, @RequestParam(name = "studentID") String studentID) throws IllegalArgumentException {
		Reminder reminder = service.createReminder(studentService.findStudentByStudentID(studentID), message);
		return convertToDto(reminder);
	}

	@GetMapping(value = { "/remindersAll", "/remindersAll/" })
	public List<ReminderDto> getAllReminders() {
		List<ReminderDto> reminderDtos = new ArrayList<>();
		for (Reminder reminder : service.getAllReminders()) {
			reminderDtos.add(convertToDto(reminder));
		}
		return reminderDtos;
	}

	@GetMapping(value = { "/reminders","/reminders/"  })
	public List<ReminderDto> getRemindersOfStudent(@RequestParam(name = "studentID") String studentID ) {
		List<ReminderDto> reminderDtos = new ArrayList<>();
		for (Reminder reminder : studentService.findStudentByStudentID(studentID).getReminder()) {
			reminderDtos.add(convertToDto(reminder));
		}
		return reminderDtos;
	}

	private ReminderDto convertToDto(Reminder reminder) {
		if (reminder == null) {
			throw new IllegalArgumentException("There is no such Reminder!");
		}
		ReminderDto reminderDto = new ReminderDto (reminder.getMessage(), reminder.getCreateDateTime());
		return reminderDto;
	}
}
