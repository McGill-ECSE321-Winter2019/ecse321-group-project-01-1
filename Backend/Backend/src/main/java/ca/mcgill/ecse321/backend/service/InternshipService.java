package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Validated
public class InternshipService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	InternshipRepository internshipRepository;
	
	@Autowired
	CourseService courseService;
	
	@Transactional
	public Internship createInternship(@ModelAttribute("internship") @Valid InternshipDto internshipDto, Student student, Course course) throws Exception {
		Internship internship = new Internship();
    	internship.setAcademicSemester(internshipDto.getAcademicSemester());
    	internship.setApplicationForm(internshipDto.getApplicationForm());
    	internship.setDocument(internshipDto.getDocument());
    	internship.setStudent(student);
    	internship.setCourse(course);

    	return internshipRepository.save(internship);
	}

	@Transactional
	public 	Internship findInternshipById(int id) {
		return internshipRepository.findInternshipById(id);
	}

	@Transactional
	public Internship findByIdAndStudentStudentID(int id, String studentID) {
		return internshipRepository.findByIdAndStudentStudentID(id, studentID);
	}
	
	@Transactional
	public Internship findByIdAndStudent(int id, Student student) {
		return internshipRepository.findByIdAndStudent(id, student);
	}
	
	public boolean[] generateProgress(Internship internship) {
		boolean[] progress = new boolean[4];
		Set<Document> documents = internship.getDocument();
		if (documents == null) return progress;
		
		for (Document d:documents) {
			switch(d.getDocumentType()){
			case CONTRACT :
				progress[0] = true;
				break;
			case WORK_REPORT:
				progress[1] = true;
				break;
				
			case TECHNICAL_REPORT:
				progress[2] = true;
				break;
				
			case EVALUATION:
				progress[3] = true;

			}
		}
		return progress;
	}
	
    public InternshipDto toDto(Internship internship) {
    	InternshipDto internshipDto = new InternshipDto();
    	internshipDto.setAcademicSemester(internship.getAcademicSemester());
    	internshipDto.setApplicationForm(internship.getApplicationForm());
    	internshipDto.setCourse(courseService.toDto(internship.getCourse()));
    	internshipDto.setDocument(internship.getDocument());
    	internshipDto.setStudent(internship.getStudent());
    	internshipDto.setId(internship.getId());
    	internshipDto.setProgress(generateProgress(internship));
    	return internshipDto;
    }
    
	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	@Transactional
	public List<Internship> getAll() {
		return toList(internshipRepository.findAll());
	}
    
}

