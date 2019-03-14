package ca.mcgill.ecse321.backend.controllers;


import ca.mcgill.ecse321.backend.controller.InternshipController;
import ca.mcgill.ecse321.backend.model.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//import
//ca.mcgill.ecse321.backend.BackendApplicationController;

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
