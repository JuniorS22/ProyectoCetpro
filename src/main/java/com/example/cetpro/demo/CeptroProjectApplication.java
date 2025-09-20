package com.example.cetpro.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CeptroProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CeptroProjectApplication.class, args);
	}

}
