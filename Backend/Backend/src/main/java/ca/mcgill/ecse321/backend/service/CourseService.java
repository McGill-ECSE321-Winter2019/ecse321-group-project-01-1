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

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.ReminderRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.dto.StudentDto;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class CourseService {
	
	@Autowired
	CourseRepository courseRepository;
	
	@Transactional
	public Course create(@ModelAttribute("course") @Valid CourseDto courseDto){
		Course course = new Course();
		course.setCourseID(courseDto.getCourseID());
		course = courseRepository.save(course);
		return course;
	}
	
	@Transactional
	public Course findCourseById(int id) {
		return courseRepository.findCourseById(id);
	}
	
	@Transactional
	public Course update(Course course){
		course = courseRepository.save(course);
		return course;
	}
    
	@Transactional
	public List<Course> getAll() {
		return toList(courseRepository.findAll());
	}
	
    public CourseDto toDto(Course course) {
    	CourseDto courseDto = new CourseDto();
    	courseDto.setId(course.getId());
    	courseDto.setCourseID(course.getCourseID());
		return courseDto;
    	
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
    
}

