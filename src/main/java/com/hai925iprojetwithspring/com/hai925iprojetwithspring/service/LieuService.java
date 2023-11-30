package com.hai925iprojetwithspring.com.hai925iprojetwithspring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Lieu;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository.ILieuRepository;

@Service
public class LieuService {

	@Autowired
	private ILieuRepository lieuRepository;

	public Lieu getLieu(final String id) {
		return lieuRepository.findById(id).get();
	}

	public long getNbLieux() {
		return lieuRepository.count();
	}

	public Iterable<Lieu> getLieux() {
		return lieuRepository.findAll();
	}

	public void deleteLieu(final String id) {
		lieuRepository.deleteById(id);
	}

	public Lieu saveLieu(Lieu lieu) {
		Lieu savedLieu = lieuRepository.save(lieu);
		return savedLieu;
	}

}
