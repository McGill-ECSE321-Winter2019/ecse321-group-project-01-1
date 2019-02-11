package ca.mcgill.ecse321.BackendApplication.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.backend.model.ApplicationForm;

public interface ApplicationForm extends CrudRepository<ApplicationForm, Integer>{

	ApplicationForm findFormByID(int id);

}