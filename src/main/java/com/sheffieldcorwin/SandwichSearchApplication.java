package com.sheffieldcorwin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

import com.sheffieldcorwin.config.ApplicationConfig;
import com.sheffieldcorwin.config.SecurityConfig;

@SpringBootApplication(scanBasePackages = {"com.sheffieldcorwin.*"})
@Import({ApplicationConfig.class, SecurityConfig.class})
@EnableJdbcRepositories
public class SandwichSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(SandwichSearchApplication.class, args);
	}

}
