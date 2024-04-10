package com.smartfinancetracker.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.smartfinancetracker")
@EnableJpaRepositories(basePackages = "com.smartfinancetracker.dao")
@EntityScan("com.smartfinancetracker.models")
public class SmartFinanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartFinanceTrackerApplication.class, args);
	}

}
