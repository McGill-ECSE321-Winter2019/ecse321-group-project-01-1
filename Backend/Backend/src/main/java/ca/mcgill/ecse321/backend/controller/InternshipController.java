package ca.mcgill.ecse321.backend.controller;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;


@CrossOrigin(origins = "*")
@RestController
public class InternshipController {

	@Autowired
	BackendApplicationService service = new BackendApplicationService();
	
	@GetMapping(value = { "/progress", "/progress/" })
	public boolean[] getProgress(@RequestParam(name = "internshipId") int id) {
		
		Set<Document> documents = new HashSet<Document>();
		documents = service.readInternship(id).getDocument();
		boolean[] progress = new boolean[4];
		
		
		
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

}
