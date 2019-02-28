package ca.mcgill.ecse321.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseById(int id);
	Course findCourseByCourseID(String courseID);

}