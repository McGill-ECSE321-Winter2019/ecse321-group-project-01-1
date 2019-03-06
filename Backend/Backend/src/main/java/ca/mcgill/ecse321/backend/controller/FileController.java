package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.DocumentDto;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.AuthenticationService;
import ca.mcgill.ecse321.backend.service.InternshipService;
import ca.mcgill.ecse321.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;

@RestController
public class FileController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private InternshipService internshipService;
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/internships/{internship_id}/documents")

    public DocumentDto uploadFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("type") DocumentType type,
                                  @PathVariable(value="internship_id") int internshipId){
        Student student = authenticationService.getCurrentStudent();
        Internship i = internshipService.findByIdAndStudent(internshipId, student);
        if (i == null) throw new AccessDeniedException("");
        Document dbFile = storageService.createFile(file, i, type);
        return storageService.toDto(dbFile);
    }

    @GetMapping("/api/internships/{internship_id}/documents")
    public ArrayList<DocumentDto> showAllDocuments(
    		@PathVariable(value="internship_id") int internshipId){
        Student student = authenticationService.getCurrentStudent();
        Internship i = internshipService.findByIdAndStudent(internshipId, student);
        if (i == null) throw new AccessDeniedException("");
        ArrayList<DocumentDto> documentDtos = new ArrayList<>();
        for (Document document: storageService.readAllDocumentsByInternship(i)){
            documentDtos.add(storageService.toDto(document));
        }
        return documentDtos;
    }
    // we don't need this one for now
    // and if we need this, it should be merged with the method above
    // taking an extra argument to filter the documents by type
    /**
    @GetMapping("/api/internships/{internship_id}/documents/{document_id}")
    public DocumentDto showDocumentByTypeAndInternship(@RequestParam("type") DocumentType type,
                                                    @RequestParam("internship") Internship internship){
        return convertToDto(storageService.readDocumentByType(internship, type));
    }
    **/

    @GetMapping("/api/internships/{internship_id}/documents/{document_id}/download")
    public ResponseEntity<Resource> downloadFile(@PathVariable(value="document_id") String documentId) {
        // Load file from database
        Document document = storageService.readDocument(documentId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }
}