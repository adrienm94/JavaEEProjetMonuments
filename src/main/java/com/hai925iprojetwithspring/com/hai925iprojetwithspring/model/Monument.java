package com.hai925iprojetwithspring.com.hai925iprojetwithspring.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;

@Entity /*
 * @Entity est une annotation qui indique que la classe correspond à une table
 * de la base de données.
 */
@Table(name = "monument", schema = "hai925i")/*
 * indique le nom de la table associée et le schéma où est défini la
 * table.
 */
public class Monument {

	@Id // L’attribut geohash correspond à la clé primaire de la table, et est donc
	// annoté @Id.
	private String geohash;

	private String nom;
	private String proprietaire;

	@Column(name = "type_m")
	private String typeM;

	private float longitude;
	private float latitude;

	@ManyToOne
	@JoinColumn(name = "code_lieu") // nom de colonne de BD MySQL
	private Lieu lieu;

	@ManyToMany(mappedBy = "listeMonuments", targetEntity = Celebrite.class) // attribut de ta classe
	private List<Celebrite> listeCelebrites = new ArrayList<>();

	public Monument() {
	}

	public Monument(String nom) {
		this.nom = nom;
	}

	public Monument(String geohash, String nom, String proprietaire, String typeM, float longitude, float latitude,
			Lieu lieu, List<Celebrite> listeCelebrites) {
		this.geohash = geohash;
		this.nom = nom;
		this.proprietaire = proprietaire;
		this.typeM = typeM;
		this.longitude = longitude;
		this.latitude = latitude;
		this.lieu = lieu;
		this.listeCelebrites = listeCelebrites;
	}

	public String getGeohash() {
		return geohash;
	}

	public void setGeohash(String geohash) {
		this.geohash = geohash;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}

	public String getTypeM() {
		return typeM;
	}

	public void setTypeM(String typeM) {
		this.typeM = typeM;
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

	public Lieu getLieu() {
		return lieu;
	}

	public void setLieu(Lieu lieu) {
		this.lieu = lieu;
	}

	public List<Celebrite> getListeCelebrites() {
		return listeCelebrites;
	}

	public void setListeCelebrites(List<Celebrite> listeCelebrites) {
		this.listeCelebrites = listeCelebrites;
	}

	@Override
	public String toString() {
		String s = "Monument [geohash=" + geohash + ", nom=" + nom + ", proprietaire=" + proprietaire + ", typeM="
				+ typeM + ", longitude=" + longitude + ", latitude=" + latitude + ", lieu=" + lieu
				+ ", listeCelebrites=";

		for (Celebrite c : listeCelebrites) {
			s += c.getNom() + ",";
		}
		s += "]]";

		return s;
	}

}
