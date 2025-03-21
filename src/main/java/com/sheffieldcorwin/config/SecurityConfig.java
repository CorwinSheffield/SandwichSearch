package com.sheffieldcorwin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.sheffieldcorwin.SandwichSearchApplication;

@Configuration
public class SecurityConfig {

	private final SandwichSearchApplication sandwichSearchApplication;

	SecurityConfig(SandwichSearchApplication sandwichSearchApplication) {
		this.sandwichSearchApplication = sandwichSearchApplication;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authz -> authz.anyRequest().permitAll()).csrf(CsrfConfigurer::disable).cors(CorsConfigurer::disable);
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("user")).build();

		return new InMemoryUserDetailsManager(user);

	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
