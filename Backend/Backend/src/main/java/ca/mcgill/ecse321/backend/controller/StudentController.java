package ca.mcgill.ecse321.backend.controller;


import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.backend.dto.ReminderDto;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;

public class StudentController {
	
	BackendApplicationService service = new BackendApplicationService();
	
	public boolean[] getProgress(String studentID) {
		
		List<Document> documents = new ArrayList<>();
		documents = service.getAllDocuments();
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
