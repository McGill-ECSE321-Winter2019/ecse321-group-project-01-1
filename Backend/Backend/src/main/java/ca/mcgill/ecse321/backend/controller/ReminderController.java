package ca.mcgill.ecse321.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;

@CrossOrigin(origins = "*")
@RestController
public class ReminderController {
	
	BackendApplicationService service = new BackendApplicationService();
	
	@PostMapping(value = { "/reminders/{name}", "/reminders/{name}/" })
	public ReminderDto createReminder(@PathVariable("name") String message, String studentID) throws IllegalArgumentException {
		// @formatter:on
		Reminder reminder = service.createReminder(service.readStudent(studentID), message);
		return convertToDto(reminder);
	}
	
	@GetMapping(value = { "/reminders", "/reminders/" })
	public List<ReminderDto> getAllReminders() {
		List<ReminderDto> eventDtos = new ArrayList<>();
		for (Reminder event : service.getAllReminders()) {
			eventDtos.add(convertToDto(event));
		}
		return eventDtos;
	}

	private ReminderDto convertToDto(Reminder reminder) {
		if (reminder == null) {
			throw new IllegalArgumentException("There is no such Reminder!");
		}
				ReminderDto reminderDto = new ReminderDto (reminder.getMessage(), reminder.getCreateDateTime());
				return reminderDto;
	}
	
	
	

}
