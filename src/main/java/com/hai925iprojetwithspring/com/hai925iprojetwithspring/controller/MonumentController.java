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
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Lieu;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.model.Monument;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.CelebriteService;
import com.hai925iprojetwithspring.com.hai925iprojetwithspring.service.MonumentService;

/* Le starter “spring-boot-starter-web” nous fournit
 * tout le nécessaire pour créer un endpoint. Il faut :
	- une classe Java annotée @Controller ;
	- que les méthodes de la classe soient annotées. 
	Chaque méthode annotée devient alors un endpoint grâce aux annotations @GetMapping, 
	@PostMapping, @PatchMapping, @PutMapping, @DeleteMapping, @RequestMapping.
 */

@Controller // Spécialisation de @Component
public class MonumentController {

	@Autowired
	private MonumentService monumentService;

	@Autowired
	private CelebriteService celebriteService;

	// Méthode de contrôleur qui demande l'ensemble des monuments de la base de
	// données
	// L’annotation ci-dessous spécifie le type de requête HTTP (ici GET) et l’URL
	// correspondante.
	@GetMapping(path = "/monuments")
	public String homeMonuments(Model model) {
		model.addAttribute("listeMonuments", monumentService.getMonuments());
		model.addAttribute("nombreMonuments", monumentService.getNbMonuments());
		model.addAttribute("listeCelebrites", celebriteService.getCelebrites());
		return "homeMonuments";
	}

	// Demande un monument avec un certain identifiant (ici geohash)
	@GetMapping(path = "/monumentById")
	public String MonumentById(@RequestParam String id, Model model) {
		model.addAttribute("monument", monumentService.getMonument(id));
		return "monumentById";
	}

	// Pour récupérer les données du formulaire html, on peut utiliser l'annotation
	// @RequestParam.
	// Ici, le nom des paramètres sont ceux définis dans les attributs name des
	// éléments du formulaire.

	// Demande les monuments par propriétaire
	@GetMapping(path = "/monumentsByProprietaire")
	public String MonumentsByProprietaire(@RequestParam String propriétaire, Model model) {
		model.addAttribute("listeMonuments", monumentService.getMonumentsProprietaire(propriétaire));
		model.addAttribute("listeCelebrites", celebriteService.getCelebrites());
		return "monumentsByProprietaire";
	}

	// Demande les monuments par type de monument
	@GetMapping(path = "/monumentsByTypeM")
	public String MonumentsByTypeM(@RequestParam String typeM, Model model) {
		model.addAttribute("listeMonuments", monumentService.getMonumentsTypeM(typeM));
		model.addAttribute("listeCelebrites", celebriteService.getCelebrites());
		return "monumentsByTypeM";
	}

	// Demande les monuments par codeinsee de lieu
	@GetMapping(path = "/monumentsByCodeInsee")
	public String MonumentsByCodeInsee(@RequestParam String codeInsee, Model model) {
		model.addAttribute("listeMonuments", monumentService.getMonumentsCodeInsee(codeInsee));
		model.addAttribute("listeCelebrites", celebriteService.getCelebrites());
		return "monumentsByCodeInsee";
	}

	// Ajoute un monument
	@PostMapping(path = "/ajoutMonument")
	public String ajoutMonument(@RequestParam String id, @RequestParam String nom, @RequestParam String propriétaire,
			@RequestParam String typeM, @RequestParam float longitude, @RequestParam float latitude,
			@RequestParam String codeInseeLieu, @RequestParam(value = "celebrites[]") String celebriteA) {

		// récupère le numéro de célébrité associée
		int numCelebrite = Integer.parseInt(celebriteA.split(" ")[0]);
		Celebrite celebriteAssociee = celebriteService.getCelebrite(numCelebrite);

		// Définit la liste de célébrités associées
		List<Celebrite> listeCelebrites = new ArrayList<>();
		listeCelebrites.add(celebriteAssociee);

		// Définit le lieu
		Lieu lieu = new Lieu();
		lieu.setCodeInsee(codeInseeLieu);

		// Création et sauvegarde de l'entité de Monument
		Monument monument = new Monument(id, nom, propriétaire, typeM, longitude, latitude, lieu, listeCelebrites);
		celebriteService.getCelebrite(celebriteAssociee.getNumCelebrite()).getListeMonuments().add(monument);
		monumentService.saveMonument(monument);

		return "ajoutMonument";
	}

	// Suppression d'un monument par son identifiant (ici geohash)
	@GetMapping(path = "/suppressionMonument")
	public String suppressionMonument(@RequestParam String id) {
		monumentService.deleteMonument(id);
		return "suppressionMonument";
	}

}
