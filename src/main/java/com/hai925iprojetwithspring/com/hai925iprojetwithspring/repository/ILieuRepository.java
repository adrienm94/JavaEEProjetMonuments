package com.hai925iprojetwithspring.com.hai925iprojetwithspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Lieu;

@Repository
public interface ILieuRepository extends CrudRepository<Lieu, String> {

}
