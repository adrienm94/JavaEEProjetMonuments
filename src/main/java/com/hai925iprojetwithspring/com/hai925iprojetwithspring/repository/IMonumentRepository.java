package com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Monument;

/* @Repository est une annotation Spring 
pour indiquer que la classe est un bean, et que son rôle est de communiquer 
avec une source de données (en l'occurrence la base de données). Spécialisation de @Component*/

@Repository
public interface IMonumentRepository extends CrudRepository<Monument, String> {

	/*
	 * Définition de requêtes personnalisées qui peuvent ne pas être possibles par
	 * les méthodes fournies par l'interface CrudRepository<Monument, String>
	 */

	@Query("SELECT m FROM Monument m WHERE proprietaire=:proprietaire")
	public Iterable<Monument> findByProprietaire(@Param("proprietaire") String proprietaire);

	@Query("SELECT m FROM Monument m WHERE typeM=:typeM")
	public Iterable<Monument> findByTypeM(@Param("typeM") String typeM);

	@Query("SELECT m FROM Monument m WHERE m.lieu.codeInsee=:codeInsee")
	public Iterable<Monument> findByCodeInsee(@Param("codeInsee") String codeInsee);

}
