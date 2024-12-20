package com.infinitybyteleague.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//Metodo anotado como @Bean en donde vive toda la configuración con los métodos de SecurityFilterChain
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http 
		
		//Desactiva la protecciín CSRF (Cross-Site Request Forgery)
		//.csrf().disable() ---- Sintaxis anterior a Spring Security 6
		//Nueva Sintaxis
			.csrf((csrf) -> csrf.disable())
			//Configuración de Solicitud donde le digo que cualquier usuario puede realizar peticiones
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/api/v1/usuario/*") //Patrón para saber a donde puede realizar la petición
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			//Configura la autenticación básica
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	//Método que me permite encriptar la información del usuario
	@Bean
	public PasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	// Método para Autenticar
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
	
}
