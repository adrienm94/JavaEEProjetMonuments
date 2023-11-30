package com.hai925iprojetwithspring.com.hai925iprojetwithspring.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "lieu", schema = "hai925i")
public class Lieu {

	@Id
	@Column(name = "code_insee")
	private String codeInsee;

	@Column(name = "nom_com")
	private String nomCom;
	private float longitude;
	private float latitude;

	@ManyToOne
	@JoinColumn(name = "dep")
	private Departement dept;

	@OneToMany(mappedBy = "lieu", targetEntity = Monument.class)
	private List<Monument> monuments = new ArrayList<>(); // Utiliser les interfaces dans le type statique de la
	// variable pour que JPA gère.

	public Lieu() {
	}

	public Lieu(String codeInsee, String nomCom, float longitude, float latitude, Departement dept,
			List<Monument> monuments) {
		super();
		this.codeInsee = codeInsee;
		this.nomCom = nomCom;
		this.longitude = longitude;
		this.latitude = latitude;
		this.dept = dept;
		this.monuments = monuments;
	}

	public String getCodeInsee() {
		return codeInsee;
	}

	public void setCodeInsee(String codeInsee) {
		this.codeInsee = codeInsee;
	}

	public String getNomCom() {
		return nomCom;
	}

	public void setNomCom(String nomCom) {
		this.nomCom = nomCom;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public Departement getDep() {
		return dept;
	}

	public void setDep(Departement dept) {
		this.dept = dept;
	}

	public List<Monument> getMonuments() {
		return monuments;
	}

	public void setMonuments(List<Monument> monuments) {
		this.monuments = monuments;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Lieu [codeInsee=" + codeInsee + ", nomCom=" + nomCom + ", longitude=" + longitude + ", latitude="
				+ latitude
				// + ", dept=" + dept
				+ ", monuments=[";
		for (Monument m : monuments) {
			s += m.getNom() + ",";
		}
		s += "]]";

		return s;
	}

}
