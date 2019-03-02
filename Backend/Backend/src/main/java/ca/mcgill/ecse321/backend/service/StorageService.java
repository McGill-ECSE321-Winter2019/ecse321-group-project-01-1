package ca.mcgill.ecse321.backend.service;

import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.exception.FileNotFoundException;
import ca.mcgill.ecse321.backend.exception.FileStorageException;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.exception.FileStorageException;
import ca.mcgill.ecse321.backend.exception.FileNotFoundException;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
public class StorageService {

    @Autowired
    private DocumentRepository fileRepository;

    @Transactional
    public Document storeFile(MultipartFile file, Internship internship, DocumentType type) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Document doc;
        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            doc = new Document(fileName, file.getContentType(), file.getBytes());
        }catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
        doc.setDocumentType(type);
        doc.setInternship(internship);
        return fileRepository.save(doc);
    }

    @Transactional
    public Document getFile(int fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }

}