package com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Celebrite;

@Repository
public interface ICelebriteRepository extends CrudRepository<Celebrite, Integer> {

	/*
	 * Pas de code, car Spring Data JPA nous permet d’exécuter des requêtes SQL,
	 * sans avoir besoin de les écrire
	 */

}
