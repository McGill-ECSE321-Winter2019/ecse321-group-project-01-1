package ca.mcgill.ecse321.backend.model;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

public class DocumentTest {


    @InjectMocks
    private Document document;

    @Mock
    private Internship dummyInternship;

    @Mock
    private Internship anotherDummyInternship;

    @Before
    public void setDocument() {
        document = new Document();
        document.setData("lmao".getBytes());
        document.setDocumentType(DocumentType.CONTRACT);
        document.setInternship(dummyInternship);
        document.setFileName("dummyFile.txt");
        document.setFileType("application/octet-stream");
        document.setId("abc-efg");
        document.setSize(10000);
    }

    @Test
    @Transactional
    public void setId() {
        assertEquals("abc-efg",document.getId());
        document.setId("someId");
        assertEquals("someId",document.getId());
    }
    @Transactional
    @Test
    public void setSize() {
        assertEquals(10000,document.getSize());
        document.setSize(100);
        assertEquals(100,document.getSize());
    }
    @Transactional
    @Test
    public void getSize() {
        assertEquals(10000,document.getSize());
    }
    @Transactional
    @Test
    public void getInternship() {
        assertEquals(dummyInternship,document.getInternship());
        document.setInternship(anotherDummyInternship);
        assertEquals(anotherDummyInternship,document.getInternship());
    }
    @Transactional
    @Test
    public void setInternship() {
        assertEquals("abc-efg",document.getId());
        document.setId("someId");
        assertEquals("someId",document.getId());
    }
    @Transactional
    @Test
    public void setFileName() {
        assertEquals("dummyFile.txt",document.getFileName());
        document.setFileName("dumbFile.txt");
        assertEquals("dumbFile.txt",document.getFileName());
    }
    @Transactional
    @Test
    public void setFileType() {
        assertEquals("application/octet-stream",document.getFileType());
        document.setFileType("text/plain");
        assertEquals("text/plain",document.getFileType());
    }
    @Transactional
    @Test
    public void getFileName() {
        assertEquals("dummyFile.txt",document.getFileName());
    }
    @Transactional
    @Test
    public void getFileType() {
        assertEquals("application/octet-stream",document.getFileType());
    }
    @Transactional
    @Test
    public void getDocumentType() {
        assertEquals(DocumentType.CONTRACT,document.getDocumentType());
    }
    @Transactional
    @Test
    public void setDocumentType() {
        assertEquals("abc-efg",document.getId());
        document.setId("someId");
        assertEquals("someId",document.getId());
    }
    @Transactional
    @Test
    public void getId() {
        assertEquals("abc-efg",document.getId());
    }
}
