package ca.mcgill.ecse321.backend.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ca.mcgill.ecse321.backend.dto.DocumentDto;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Student;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StorageService;

@RestController
@CrossOrigin(origins = "*")

public class FileController {

	@Autowired
	private StorageService storageService;
	@Autowired
	private InternshipService internshipService;
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * This method uploads a file via a POST request
	 * 
	 * @param file         File
	 * @param type         File Type
	 * @param internshipId Internship ID
	 * @return Document DTO
	 */
	@PostMapping("/api/internships/{internship_id}/documents")
	public DocumentDto uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("type") DocumentType type,
			@PathVariable(value = "internship_id") int internshipId) {
		Student student = authenticationService.getCurrentStudent();
		Internship i = internshipService.findByIdAndStudent(internshipId, student);
		if (i == null)
			throw new AccessDeniedException("");
		Document dbFile = storageService.createFile(file, i, type);
		return storageService.toDto(dbFile);
	}

	/**
	 * This method gets all documents of an internship via a GET request
	 * 
	 * @param internshipId Internship ID
	 * @return ArrayList of Document DTOs
	 */
	@GetMapping("/api/internships/{internship_id}/documents")
	public ArrayList<DocumentDto> showAllDocuments(@PathVariable(value = "internship_id") int internshipId) {
		Student student = authenticationService.getCurrentStudent();
		Internship i = internshipService.findByIdAndStudent(internshipId, student);
		if (i == null)
			throw new AccessDeniedException("");
		ArrayList<DocumentDto> documentDtos = new ArrayList<>();
		for (Document document : storageService.readAllDocumentsByInternship(i)) {
			documentDtos.add(storageService.toDto(document));
		}
		return documentDtos;
	}

	/**
	 * This method gets a document via a GET request
	 * 
	 * @param documentId   Document ID
	 * @param internshipId Internship ID
	 * @return Document
	 */
	@GetMapping("/api/internships/{internship_id}/documents/{document_id}/download")
	public ResponseEntity<Resource> downloadFile(@PathVariable(value = "document_id") String documentId,
			@PathVariable(value = "internship_id") int internshipId) {
		// Load file from database
		Student student = authenticationService.getCurrentStudent();

		Internship i = internshipService.findByIdAndStudent(internshipId, student);

		Document document = storageService.readDocument(documentId);
		if (i == null || i != document.getInternship())
			throw new AccessDeniedException("");

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(document.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
				.body(new ByteArrayResource(document.getData()));
	}

	/**
	 * This method gets a document via a GET request
	 *
	 * @param documentId Document ID
	 * @return Document
	 */

	@GetMapping("/external/documents/{document_id}/download")
	public ResponseEntity<Resource> downloadFileExternal(@PathVariable(value = "document_id") String documentId) {
		// Load file from database
		Document document = storageService.readDocument(documentId);

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(document.getFileType()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
				.body(new ByteArrayResource(document.getData()));
	}
}