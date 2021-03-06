package com.tecsup.petclinic.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.OwnerRepository;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@Service
public class OwnerServiceImpl implements OwnerService {

	private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
	
	@Autowired
	OwnerRepository repo;
	
	@Override
	public Owner create(Owner owner) {
		return repo.save(owner);
	}

	@Override
	public Owner update(Owner owner) {
		return repo.save(owner);
	}

	@Override
	public void delete(Long id) throws OwnerNotFoundException {
		Owner owner = findById(id);
		repo.delete(owner);
	}

	@Override
	public Owner findById(long id) throws OwnerNotFoundException {
		Optional<Owner> owner = repo.findById(id);

		if (!owner.isPresent()) //si no se encuentra
			throw new OwnerNotFoundException("ERROR: NO SE ENCONTRO EL OWNER!");
			
		return owner.get(); //si se encontro, que devuelva el owner
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		//obtiene todos los owners por su apellido y lo guarda en una lista
		List<Owner> owners = repo.findByLastName(lastName);
		owners.stream().forEach(owner -> logger.info("" + owner));

		return owners;
	}

	@Override
	public List<Owner> findByFirstName(String firstName) {
		List<Owner> owners = repo.findByFirstName(firstName);
		owners.stream().forEach(owner -> logger.info("" + owner));

		return owners;
	}

	@Override
	public Iterable<Owner> findAll() {
		return repo.findAll(); //devuelve todos los owners
	}

}
