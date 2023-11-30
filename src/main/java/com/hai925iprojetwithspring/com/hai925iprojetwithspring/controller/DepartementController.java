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
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.DepartementService;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.LieuService;

@Controller
public class DepartementController {

	@Autowired
	private DepartementService departementService;

	@Autowired
	private LieuService lieuService;

	@GetMapping(path = "/departements")
	public String homeDepartements(Model model) {
		model.addAttribute("listeDepartements", departementService.getDepartements());
		model.addAttribute("nombreDepartements", departementService.getNbDepartements());
		model.addAttribute("listeLieux", lieuService.getLieux());
		return "homeDepartements";
	}

	@GetMapping(path = "/departementById")
	public String DepartementById(@RequestParam String id, Model model) {
		model.addAttribute("departement", departementService.getDepartement(id));
		return "departementById";
	}

	@PostMapping(path = "/ajoutDepartement")
	public String ajoutDepartement(@RequestParam String id, @RequestParam String nomDep,
			@RequestParam String codeInseeChefLieu, @RequestParam String reg,
			@RequestParam(value = "lieux[]") String lieuA) {

		String codeInsee = lieuA.split(" ")[0];
		Lieu lieuAssocie = lieuService.getLieu(codeInsee);

		List<Lieu> listeLieux = new ArrayList<>();
		listeLieux.add(lieuAssocie);

		Lieu lieuChefLieu = new Lieu();
		lieuChefLieu.setCodeInsee(codeInseeChefLieu);

		Departement departement = new Departement(id, nomDep, lieuChefLieu, reg, listeLieux);
		departementService.saveDepartement(departement);

		return "ajoutDepartement";
	}

	@GetMapping(path = "/suppressionDepartement")
	public String suppressionDepartement(@RequestParam String id) {
		departementService.deleteDepartement(id);
		return "suppressionDepartement";
	}

}
