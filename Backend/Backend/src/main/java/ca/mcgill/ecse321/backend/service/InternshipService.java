package ca.mcgill.ecse321.backend.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dto.DocumentDto;
import ca.mcgill.ecse321.backend.dto.InternshipDeepDto;
import ca.mcgill.ecse321.backend.dto.InternshipDto;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;

@Service
@Validated
public class InternshipService {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	InternshipRepository internshipRepository;
	
	@Autowired
	CourseService courseService;
	
	@Autowired
	ApplicationFormService applicationFormService;

	@Autowired
	StorageService storageService;
	
	@Transactional
	public Internship create(@ModelAttribute("internship") @Valid InternshipDto internshipDto, Student student, Course course) throws Exception {
		Internship internship = new Internship();
    	internship.setAcademicSemester(internshipDto.getAcademicSemester());
    	internship.setStudent(student);
    	internship.setCourse(course);
    	internship.setYear(internshipDto.getYear());

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
	
	@Transactional
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
	
	@Transactional
    public InternshipDto toDto(Internship internship) {
    	InternshipDto internshipDto = new InternshipDto();
    	internshipDto.setAcademicSemester(internship.getAcademicSemester());
    	internshipDto.setCourse(courseService.toDto(internship.getCourse()));
    	internshipDto.setId(internship.getId());
    	internshipDto.setProgress(generateProgress(internship));
    	internshipDto.setYear(internship.getYear());
    	return internshipDto;
    }
    
    @Transactional
    public InternshipDeepDto deepToDto(Internship internship) {
    	InternshipDeepDto internshipDto = new InternshipDeepDto();
    	internshipDto.setAcademicSemester(internship.getAcademicSemester());
    	internshipDto.setApplicationForm(applicationFormService.toDto(internship.getApplicationForm()));
    	internshipDto.setCourse(courseService.toDto(internship.getCourse()));
    	internshipDto.setYear(internship.getYear());

		HashSet<DocumentDto> documentDtos = new HashSet<>();
		for (Document document : internship.getDocument()) {
			documentDtos.add(storageService.toDto(document));
		}
		internshipDto.setDocument(documentDtos);
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

