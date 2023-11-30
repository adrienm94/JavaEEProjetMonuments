package com.hai925iprojetwithspring.com.hai925iprojetwithspring.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

/* Le starter “spring-boot-starter-web” nous fournit justement 
 * tout le nécessaire pour créer un endpoint.  Il faut :
	- une classe Java annotée @Controller ;
	- que les méthodes de la classe soient annotées. 
	Chaque méthode annotée devient alors un endpoint grâce aux annotations @GetMapping, 
	@PostMapping, @PatchMapping, @PutMapping, @DeleteMapping, @RequestMapping.
 */

@Controller // Spécialisation de @Component
public class HomeController {

	@GetMapping(path = "/")
	public String home() {
		return "home";
	}

}
