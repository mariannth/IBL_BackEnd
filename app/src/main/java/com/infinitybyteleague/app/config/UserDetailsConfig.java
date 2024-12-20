package com.infinitybyteleague.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.infinitybyteleague.app.model.Usuario;

@Configuration
public class UserDetailsConfig {
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails admin = User.builder().username("ADMIN").password(passwordEncoder.encode("password")).roles("ADMIN")
				.build();

		return new InMemoryUserDetailsManager(admin);
	}
}
