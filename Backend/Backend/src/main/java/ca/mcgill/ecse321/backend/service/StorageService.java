package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.exception.FileNotFoundException;
import ca.mcgill.ecse321.backend.exception.FileStorageException;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class StorageService {

    @Autowired
    private DocumentRepository documentRepository;

    @Transactional
    public Document createFile(MultipartFile file, Internship internship, DocumentType type) {
            // Normalize file name
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Document doc;
            try {
                // Check if the file's name contains invalid characters
                if (fileName.contains("..") || fileName.contains("/") || fileName.contains("^")) {
                    throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
                }
                doc = documentRepository.findDocumentByInternshipAndDocumentType(internship,type);

                // create new object if nothing was there before, else just update current object
                if(doc == null){
                    doc = new Document(fileName, file.getContentType(), file.getBytes(),file.getSize());
                }
            }catch (IOException ex){
                throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
            }
            doc.setDocumentType(type);
            doc.setInternship(internship);
            return documentRepository.save(doc);
        }

    @Transactional
    public List<Document> readAllDocumentsByInternship(Internship internship){
        if(internship == null){
            throw new IllegalArgumentException("Internship cannot be null");
        }
        return new ArrayList<>(documentRepository.findDocumentByInternship(internship));
    }

    @Transactional
    public Document readDocumentByType(Internship internship, DocumentType type){
        return documentRepository.findDocumentByInternshipAndDocumentType(internship,type);
    }

    @Transactional
    public Document readDocument(String fileId) {
        return documentRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }
}