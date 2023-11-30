package com.hai925iprojetwithspring.com.hai925iprojetwithspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Departement;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository.IDepartementRepository;

@Service
public class DepartementService {

	@Autowired
	private IDepartementRepository departementRepository;

	public Departement getDepartement(final String id) {
		return departementRepository.findById(id).get();
	}

	public long getNbDepartements() {
		return departementRepository.count();
	}

	public Iterable<Departement> getDepartements() {
		return departementRepository.findAll();
	}

	public void deleteDepartement(final String id) {
		departementRepository.deleteById(id);
	}

	public Departement saveDepartement(Departement departement) {
		Departement savedDepartement = departementRepository.save(departement);
		return savedDepartement;
	}

}
