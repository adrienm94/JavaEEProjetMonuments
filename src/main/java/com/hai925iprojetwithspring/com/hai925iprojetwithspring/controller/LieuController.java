package com.hai925iprojetwithspring.com.hai925iprojetwithspring.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Departement;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Lieu;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Monument;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.LieuService;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.MonumentService;

@Controller
public class LieuController {

	@Autowired
	private LieuService lieuService;

	@Autowired
	private MonumentService monumentService;

	@GetMapping(path = "/lieux")
	public String homeLieux(Model model) {
		model.addAttribute("listeLieux", lieuService.getLieux());
		model.addAttribute("nombreLieux", lieuService.getNbLieux());
		model.addAttribute("listeMonuments", monumentService.getMonuments());
		return "homeLieux";
	}

	@GetMapping(path = "/lieuById")
	public String CelebriteById(@RequestParam String id, Model model) {
		model.addAttribute("lieu", lieuService.getLieu(id));
		return "lieuById";
	}

	@PostMapping(path = "/ajoutLieu")
	public String ajoutCelebrite(@RequestParam String id, @RequestParam String commune, @RequestParam float latitude,
			@RequestParam float longitude, @RequestParam String depLieu,
			@RequestParam(value = "monuments[]") String monumentA) {

		String geohash = monumentA.split(" ")[0];
		Monument monumentAssocie = monumentService.getMonument(geohash);

		List<Monument> listeMonuments = new ArrayList<>();
		listeMonuments.add(monumentAssocie);

		Departement departement = new Departement();
		departement.setDep(depLieu);

		Lieu lieu = new Lieu(id, commune, longitude, latitude, departement, listeMonuments);
		lieuService.saveLieu(lieu);

		return "ajoutLieu";
	}

	@GetMapping(path = "/suppressionLieu")
	public String suppressionMonument(@RequestParam String id) {
		lieuService.deleteLieu(id);
		return "suppressionLieu";
	}

}
