package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.dto.DocumentDto;
import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;

@RestController
public class FileController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private BackendApplicationService service;

    @PostMapping("/uploadFile")
    public DocumentDto uploadFile(@RequestParam("file") MultipartFile file,
                                  @RequestParam("type") DocumentType type,
                                  @RequestParam("internship") Internship internship){

        Document dbFile = storageService.createFile(file, internship, type);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new DocumentDto(dbFile.getFileName(), fileDownloadUri, file.getContentType(), file.getSize(), type);
    }

    @GetMapping("/showAllFiles")
    public ArrayList<DocumentDto> showAllDocuments(@RequestParam("internship") Internship internship){
        ArrayList<DocumentDto> documentDtos = new ArrayList<>();
        for (Document document: storageService.readAllDocumentsByInternship(internship)){
            documentDtos.add(convertToDto(document));
        }
        return documentDtos;
    }

    @GetMapping("/showFile")
    public DocumentDto showDocumentByTypeAndInternship(@RequestParam("type") DocumentType type,
                                                    @RequestParam("internship") Internship internship){
        return convertToDto(storageService.readDocumentByType(internship, type));
    }

    @GetMapping("/downloadFile/")
    public ResponseEntity<Resource> downloadFile(@RequestParam("file_id") String fileId) {
        // Load file from database
        Document document = storageService.readDocument(Integer.parseInt(fileId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }
    public DocumentDto convertToDto(Document document){
        if(document == null){
            throw new IllegalArgumentException("There is no such Document!");
        }
        return new DocumentDto(document.getFileName(),document.getPath(),document.getFileType(),document.getSize(),document.getDocumentType());
    }

}