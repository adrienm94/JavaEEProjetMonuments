package com.hai925iprojetwithspring.com.hai925iprojetwithspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Monument;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository.IMonumentRepository;

@Service // une spécialisation de @Component
public class MonumentService {

	@Autowired
	private IMonumentRepository monumentRepository;

	public Monument getMonument(final String id) {
		return monumentRepository.findById(id).get();
	}

	public long getNbMonuments() {
		return monumentRepository.count();
	}

	public Iterable<Monument> getMonumentsProprietaire(final String proprietaire) {
		return monumentRepository.findByProprietaire(proprietaire);
	}

	public Iterable<Monument> getMonuments() {
		return monumentRepository.findAll();
	}

	public void deleteMonument(final String id) {
		monumentRepository.deleteById(id);
	}

	public Monument saveMonument(Monument monument) {
		Monument savedMonument = monumentRepository.save(monument);
		return savedMonument;
	}

	public Iterable<Monument> getMonumentsTypeM(String typeM) {
		return monumentRepository.findByTypeM(typeM);
	}

	public Iterable<Monument> getMonumentsCodeInsee(String codeInsee) {
		return monumentRepository.findByCodeInsee(codeInsee);
	}

}
