package com.hai925iprojetwithspring.com.hai925iprojetwithspring.controller;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Celebrite;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Monument;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.CelebriteService;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.MonumentService;

@Controller
public class CelebriteController {

	@Autowired
	private CelebriteService celebriteService;

	@Autowired
	private MonumentService monumentService;

	@GetMapping(path = "/celebrites")
	public String homeCelebrites(Model model) {
		model.addAttribute("listeCelebrites", celebriteService.getCelebrites());
		model.addAttribute("nombreCelebrites", celebriteService.getNbCelebrites());
		model.addAttribute("listeMonuments", monumentService.getMonuments());
		return "homeCelebrites"; // identifiant de la vue à afficher en réponse à l'utilisateur
	}

	@GetMapping(path = "/celebriteById")
	public String CelebriteById(@RequestParam int id, Model model) {
		model.addAttribute("celebrite", celebriteService.getCelebrite(id));
		return "celebriteById"; // identifiant de la vue à afficher en réponse à l'utilisateur
	}

	@PostMapping(path = "/ajoutCelebrite")
	public String ajoutCelebrite(@RequestParam int id, @RequestParam String nom, @RequestParam String prénom,
			@RequestParam String nationalité, @RequestParam String siècle,
			@RequestParam(value = "monuments[]") String monumentA) {

		String geohash = monumentA.split(" ")[0];
		Monument monumentAssocie = monumentService.getMonument(geohash);

		List<Monument> listeMonuments = new ArrayList<>();
		listeMonuments.add(monumentAssocie);

		Celebrite celebrite = new Celebrite(id, nom, prénom, nationalité, siècle, listeMonuments);
		celebriteService.saveCelebrite(celebrite);

		return "ajoutCelebrite";
	}

	@GetMapping(path = "/suppressionCelebrite")
	public String suppressionMonument(@RequestParam int id) {
		celebriteService.deleteCelebrite(id);
		return "suppressionCelebrite";
	}
}
