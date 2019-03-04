package ca.mcgill.ecse321.backend.controller;

import ca.mcgill.ecse321.backend.model.*;
import ca.mcgill.ecse321.backend.dto.UploadFileResponse;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import ca.mcgill.ecse321.backend.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.print.Doc;
import java.util.ArrayList;

@RestController
public class FileController {

    @Autowired
    private StorageService DBFileStorageService;
    @Autowired
    private BackendApplicationService service;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file,
                                         @RequestParam("type") DocumentType type,
                                         @RequestParam("internship") Internship internship){

        Document dbFile = DBFileStorageService.storeFile(file, internship, type);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(dbFile.getId())
                .toUriString();

        return new UploadFileResponse(dbFile.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @GetMapping("/showAllFiles")
    public ArrayList<Document> showAllDocuments(@RequestParam("internship") Internship internship){
        return new ArrayList<>(DBFileStorageService.getAllDocumentsByInternship(internship));
    }

    @GetMapping("/showFile")
    public Document showDocumentByTypeAndInternship(@RequestParam("type") DocumentType type,
                                                    @RequestParam("internship") Internship internship){
        return service.readDocumentByType(internship, type);
    }

    @GetMapping("/downloadFile/")
    public ResponseEntity<Resource> downloadFile(@RequestParam("file_id") String fileId) {
        // Load file from database
        Document document = DBFileStorageService.getFile(Integer.parseInt(fileId));

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(document.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getFileName() + "\"")
                .body(new ByteArrayResource(document.getData()));
    }
}