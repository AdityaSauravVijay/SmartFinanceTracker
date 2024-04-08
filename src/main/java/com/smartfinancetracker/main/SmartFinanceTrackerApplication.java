package com.smartfinancetracker.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com")
public class SmartFinanceTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartFinanceTrackerApplication.class, args);
	}

}
