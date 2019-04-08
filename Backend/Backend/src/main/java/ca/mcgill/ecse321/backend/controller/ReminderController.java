package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.ReminderService;
import ca.mcgill.ecse321.backend.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ReminderController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private ReminderService reminderService;

	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * This method creates a reminder via a POST request
	 * 
	 * @param message   Message
	 * @param studentID Student ID
	 * @return Reminder DTO
	 * @throws IllegalArgumentException
	 */
	@PostMapping(value = { "/external/students/{student_id}/reminders", "/external/students/{student_id}/reminders/" })
	public ReminderDto createReminder(@RequestParam(name = "message") String message,
			@PathVariable(value = "student_id") String studentID) throws IllegalArgumentException {
		Student student = studentService.findStudentByStudentID(studentID);
		if (student == null) {
			throw new IllegalArgumentException("No student with that id");
		}

		Reminder reminder = reminderService.create(new ReminderDto(message), student);
		return reminderService.toDto(reminder);
	}

	/**
	 * This method gets all reminders of a student via a GET request
	 * 
	 * @param studentID Student ID
	 * @return List of Reminder DTOs
	 */
	@GetMapping(value = { "/external/students/{student_id}/reminders", "/external/students/{student_id}/reminders/" })
	public List<ReminderDto> getAllStudentReminders(@PathVariable(value = "student_id") String studentID) {
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
	/**
	 * This method gets all reminders of a student via a GET request
	 * 
	 * @return List of Reminder DTOs
	 */
	@GetMapping(value = { "/api/reminders", "/api/reminders/" })
	public List<ReminderDto> getRemindersOfStudent() {
		Student student = authenticationService.getCurrentStudent();
		List<ReminderDto> reminderDtos = new ArrayList<>();
		if (student.getReminder() == null)
			return reminderDtos;
		for (Reminder reminder : student.getReminder()) {
			reminderDtos.add(reminderService.toDto(reminder));
		}
		return reminderDtos;
	}

	/**
	 * This method gets a reminder via a GET request
	 * 
	 * @param reminderId Reminder ID
	 * @return Reminder DTO
	 */
	@GetMapping(value = { "/api/reminders/{reminder_id}", "/api/reminders/{reminder_id}/" })
	public ReminderDto getReminder(@PathVariable(value = "reminder_id") int reminderId) {
		Student student = authenticationService.getCurrentStudent();
		Reminder r = reminderService.findReminderByIdAndStudent(reminderId, student);
		if (r == null)
			throw new AccessDeniedException("");
		return reminderService.toDto(r);
	}

}
