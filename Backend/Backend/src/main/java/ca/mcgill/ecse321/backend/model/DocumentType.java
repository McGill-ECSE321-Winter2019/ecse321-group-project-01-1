package ca.mcgill.ecse321.backend.model;

import javax.persistence.Entity;

@Entity
public enum DocumentType{
	CONTRACT, WORK_REPORT, TECHNICAL_REPORT, EVALUATION, 
}
