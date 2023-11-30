package com.hai925iprojetwithspring.com.hai925iprojetwithspring.model;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "departement", schema = "hai925i")
public class Departement {

	@Id
	private String dep;

	@Column(name = "nom_dep")
	private String nomDep;

	@OneToOne
	@JoinColumn(name = "chef_lieu")
	private Lieu chefLieu;

	private String reg;

	@OneToMany(mappedBy = "dept", targetEntity = Lieu.class)
	private List<Lieu> lieux = new ArrayList<>();

	public Departement() {
	}

	public Departement(String dep, String nomDep, Lieu chefLieu, String reg, List<Lieu> lieux) {
		this.dep = dep;
		this.nomDep = nomDep;
		this.chefLieu = chefLieu;
		this.reg = reg;
		this.lieux = lieux;
	}

	public String getDep() {
		return dep;
	}

	public void setDep(String dep) {
		this.dep = dep;
	}

	public String getNomDep() {
		return nomDep;
	}

	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}

	public Lieu getChefLieu() {
		return chefLieu;
	}

	public void setChefLieu(Lieu chefLieu) {
		this.chefLieu = chefLieu;
	}

	public String getReg() {
		return reg;
	}

	public void setReg(String reg) {
		this.reg = reg;
	}

	public List<Lieu> getLieux() {
		return lieux;
	}

	public void setLieux(List<Lieu> lieux) {
		this.lieux = lieux;
	}

	@Override
	public String toString() {
		String s = "";
		s += "Departement [dep=" + dep + ", nomDep=" + nomDep + ", chefLieu=" + chefLieu + ", reg=" + reg + ", lieux=[";

		for (Lieu l : lieux) {
			s += l.getNomCom() + ", ";
		}
		s += "]]";
		return s;
	}

}
