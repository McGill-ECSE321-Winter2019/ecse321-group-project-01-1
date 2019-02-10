package ca.mcgill.ecse321.backend.model;

import javax.persistence.Entity;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;

@Entity
public class Reminder{
	
	private String message;
	
	@CreationTimestamp
	private Time createDateTime;
	
	private Time readDateTime;
	
}
