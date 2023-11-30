package com.hai925iprojetwithspring.com.hai925iprojetwithspring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // configuration pour la partie web security
@EnableWebSecurity
public class SecurityConfig {

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable());
	}

	// On dit quels sont les chemins qui sont autorisés pour un utilisateur donné
	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/", "/celebrites", "/celebriteById", "/departements", "/departementById", "/lieux",
					"/lieuById", "/monuments", "/monumentById", "/monumentByProprietaire", "/monumentByTypeM",
					"/monumentByCodeInsee").permitAll();
			auth.requestMatchers("ajoutCelebrite", "/suppressionCelebrite", "/ajoutMonument", "/suppressionMonument",
					"/ajoutLieu", "/suppressionLieu", "/ajoutDepartement", "/suppressionDepartement").hasRole("ADMIN");

			// Pour désactiver la protection csrf, ce qui évite une erreur 403
			try {
				auth.anyRequest().authenticated().and().csrf(csrf -> csrf.disable());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		})

				.formLogin(Customizer.withDefaults()).build();

	}

	@Bean
	UserDetailsService users() {
		UserDetails user = User.builder() // l’objet UserDetails permet de modéliser un utilisateur.
				.username("user").password(passwordEncoder().encode("user")).roles("USER").build();
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
