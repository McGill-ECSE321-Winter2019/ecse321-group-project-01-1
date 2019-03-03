package ca.mcgill.ecse321.backend.controller;


import java.util.HashSet;
import java.util.Set;

import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;

public class InternshipController {
	
	BackendApplicationService service = new BackendApplicationService();
	
	public boolean[] getProgress(int id) {
		
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
