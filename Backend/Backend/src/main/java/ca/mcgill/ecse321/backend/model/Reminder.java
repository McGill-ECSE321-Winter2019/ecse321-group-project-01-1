package ca.mcgill.ecse321.backend.model;

import javax.persistence.Entity;
import java.sql.Time;

@Entity
public class Reminder{
	
	private String message;
	private Time createdAt;
	private boolean markedAsRead;
	
}
