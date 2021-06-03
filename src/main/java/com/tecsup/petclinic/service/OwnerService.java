package com.tecsup.petclinic.service;

import java.util.List;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

public interface OwnerService {
	//operaciones crud
	Owner create(Owner owner);
	Owner update(Owner owner);
	void delete(Long id) throws OwnerNotFoundException;

	//busquedas
	Owner findById(long id) throws OwnerNotFoundException;
	List<Owner> findByLastName(String lastName);
	List<Owner> findByFirstName(String firstName);

	Iterable<Owner> findAll();

}
