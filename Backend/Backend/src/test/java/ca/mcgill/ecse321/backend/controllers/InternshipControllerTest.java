package ca.mcgill.ecse321.backend.controllers;


import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.backend.controller.InternshipController;
import ca.mcgill.ecse321.backend.dao.ApplicationFormRepository;
import ca.mcgill.ecse321.backend.dao.CourseRepository;
import ca.mcgill.ecse321.backend.dao.DocumentRepository;
import ca.mcgill.ecse321.backend.dao.InternshipRepository;
import ca.mcgill.ecse321.backend.dao.ReminderRepository;
import ca.mcgill.ecse321.backend.dao.StudentRepository;
import ca.mcgill.ecse321.backend.service.BackendApplicationService;
import org.mockito.invocation.InvocationOnMock;
import org.springframework.test.context.junit4.SpringRunner;
//import 
//ca.mcgill.ecse321.backend.BackendApplicationController;
import ca.mcgill.ecse321.backend.model.ApplicationForm;
import ca.mcgill.ecse321.backend.model.Course;
import ca.mcgill.ecse321.backend.model.Document;
import ca.mcgill.ecse321.backend.model.DocumentType;
import ca.mcgill.ecse321.backend.model.Internship;
import ca.mcgill.ecse321.backend.model.Reminder;
import ca.mcgill.ecse321.backend.model.Student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.assertj.core.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InternshipControllerTest {
	
	private InternshipController internshipController;
	
	@Mock
	private Document contract;
	@Mock
	private Document work_report;
	@Mock
	private Document technical_report;
	@Mock
	private Document evaluation;
	
	@Before
	public void setupMock() {
		contract = mock(Document.class);
		work_report = mock(Document.class);
		technical_report = mock(Document.class);
		evaluation = mock(Document.class);
	}
	
	@Before
	public void setupMockOutput() {
		when(contract.getFileType()).thenReturn("CONTRACT");
		when(work_report.getFileType()).thenReturn("WORK_REPORT");
		when(technical_report.getFileType()).thenReturn("TECHNICAL_REPORT");
		when(evaluation.getFileType()).thenReturn("EVALUATION");
	}
	
	@Test
	public void test() {
		assertEquals(1,1);
	}

}
