package ca.mcgill.ecse321.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;

@Entity
public class Reminder{
    @Id
    @GeneratedValue
    private int id;
	
	private String message;
	
	@CreationTimestamp
	private Time createDateTime;
	
	private Time readDateTime;
	
}
