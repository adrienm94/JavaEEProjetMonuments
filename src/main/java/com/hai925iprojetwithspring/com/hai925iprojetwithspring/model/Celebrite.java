package com.hai925iprojetwithspring.com.hai925iprojetwithspring.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;

@Entity 
@Table(name = "celebrite", schema = "hai925i") 
public class Celebrite {

	@Id
	@Column(name = "num_celebrite")
	private int numCelebrite;

	private String nom;
	private String prenom;
	private String nationalite;
	private String epoque;

	@ManyToMany
	@JoinTable(name = "associea", joinColumns = @JoinColumn(name = "num_celebrite"), inverseJoinColumns = @JoinColumn(name = "code_m"))
	private List<Monument> listeMonuments = new ArrayList<>();

	public Celebrite() {
	}

	public Celebrite(int numCelebrite, String nom, String prenom, String nationalite, String epoque,
			List<Monument> listeMonuments) {
		this.numCelebrite = numCelebrite;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
		this.epoque = epoque;
		this.listeMonuments = listeMonuments;
	}

	public int getNumCelebrite() {
		return numCelebrite;
	}

	public void setNumCelebrite(int numCelebrite) {
		this.numCelebrite = numCelebrite;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNationalite() {
		return nationalite;
	}

	public void setNationalite(String nationalite) {
		this.nationalite = nationalite;
	}

	public String getEpoque() {
		return epoque;
	}

	public void setEpoque(String epoque) {
		this.epoque = epoque;
	}

	public List<Monument> getListeMonuments() {
		return listeMonuments;
	}

	public void setListeMonuments(List<Monument> listeMonuments) {
		this.listeMonuments = listeMonuments;
	}

	@Override
	public String toString() {
		String s = "Celebrite [numCelebrite=" + numCelebrite + ", nom=" + nom + ", prenom=" + prenom + ", nationalite="
				+ nationalite + ", epoque=" + epoque + ", listeMonuments=";

		for (Monument m : listeMonuments) {
			s += m.getNom() + ",";
		}
		s += "]]";

		return s;

	}

}
