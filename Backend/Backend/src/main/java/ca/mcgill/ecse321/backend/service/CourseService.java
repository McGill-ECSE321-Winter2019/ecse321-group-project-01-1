package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dto.CourseDto;
import ca.mcgill.ecse321.backend.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

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

