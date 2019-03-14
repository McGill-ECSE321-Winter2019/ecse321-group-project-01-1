package ca.mcgill.ecse321.backend.dao;

import ca.mcgill.ecse321.backend.model.ApplicationForm;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationFormRepository extends CrudRepository<ApplicationForm, Integer>{

	ApplicationForm findApplicationFormById(int id);
}