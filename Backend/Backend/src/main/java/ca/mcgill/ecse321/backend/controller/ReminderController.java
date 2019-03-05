package ca.mcgill.ecse321.backend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.ReminderService;
import ca.mcgill.ecse321.backend.service.StudentService;

@CrossOrigin(origins = "*")
@RestController
public class ReminderController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ReminderService reminderService;
	
    @Autowired
    private AuthenticationService authenticationService;

	@PostMapping(value = { "/external/students/{student_id}/reminders", "/external/students/{student_id}/reminders/" })
	public ReminderDto createReminder(
			@RequestParam(name = "message") String message, 
			@PathVariable(value="student_id") String studentID
			) throws IllegalArgumentException {
		Student student = studentService.findStudentByStudentID(studentID);
		if (student == null) {
			throw new IllegalArgumentException("No student with that idd");
		}
		
		Reminder reminder = reminderService.create(new ReminderDto(message), student);
		return reminderService.toDto(reminder);
	}


	@GetMapping(value = { "/external/students/{student_id}/reminders", "/external/students/{student_id}/reminders/" })
	public List<ReminderDto> getAllStudentReminders(@PathVariable(value="student_id") String studentID) {
		Student student = studentService.findStudentByStudentID(studentID);
		if (student == null) {
			throw new IllegalArgumentException("No student with that id");
		}
		List<ReminderDto> reminderDtos = new ArrayList<>();
		for (Reminder reminder : student.getReminder()) {
			reminderDtos.add(reminderService.toDto(reminder));
		}
		return reminderDtos;
	}
	
	// internal call
	@GetMapping(value = { "/api/reminders", "/api/reminders/"  })
	public List<ReminderDto> getRemindersOfStudent() {
		Student student = authenticationService.getCurrentStudent();
		List<ReminderDto> reminderDtos = new ArrayList<>();
		if (student.getReminder() == null) return reminderDtos;
		for (Reminder reminder : student.getReminder()) {
			reminderDtos.add(reminderService.toDto(reminder));
		}
		return reminderDtos;
	}
	
	@GetMapping(value = { "/api/reminders/{reminder_id}", "/api/reminders/{reminder_id}/"  })
	public ReminderDto getReminder(@PathVariable(value="reminder_id") int reminderId) {
		Student student = authenticationService.getCurrentStudent();
		Reminder r = reminderService.findReminderByIdAndStudent(reminderId, student);
		if (r == null) throw new AccessDeniedException("");
		return reminderService.toDto(r);
	}


}
