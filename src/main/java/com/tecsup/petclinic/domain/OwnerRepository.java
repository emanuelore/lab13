package com.tecsup.petclinic.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends CrudRepository<Owner, Long>{
	List<Owner> findByLastName(String lastName); //busca owners por apellido
	List<Owner> findByFirstName(String firstName); //busca owners por nombre
}
