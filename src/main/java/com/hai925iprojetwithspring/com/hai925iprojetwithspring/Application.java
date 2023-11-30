package com.hai925iprojetwithspring.com.hai925iprojetwithspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	// la méthode main permet d'exécuter l'application Spring
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	// la méthode run affiche "Bonjour" sur la console
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Bonjour");
	}

}
