package com.hai925iprojetwithspring.com.hai925iprojetwithspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Celebrite;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository.ICelebriteRepository;

@Service
public class CelebriteService {

	@Autowired
	private ICelebriteRepository celebriteRepository;

	public Celebrite getCelebrite(final int id) {
		return celebriteRepository.findById(id).get();
	}

	public Long getNbCelebrites() {
		return celebriteRepository.count();
	}

	public Iterable<Celebrite> getCelebrites() {
		return celebriteRepository.findAll();
	}

	public void deleteCelebrite(final int id) {
		celebriteRepository.deleteById(id);
	}

	public Celebrite saveCelebrite(Celebrite celebrite) {
		Celebrite savedCelebrite = celebriteRepository.save(celebrite);
		return savedCelebrite;
	}

}
