package ca.mcgill.ecse321.backend.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.ApplicationForm;

public interface ApplicationFormRepository extends CrudRepository<ApplicationForm, Integer>{

	ApplicationForm findFormById(int id);
}