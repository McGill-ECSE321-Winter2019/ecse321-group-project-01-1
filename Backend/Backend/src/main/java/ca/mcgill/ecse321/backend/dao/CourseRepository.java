package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseById(int id);
	Course findCourseByCourseID(String courseID);

}