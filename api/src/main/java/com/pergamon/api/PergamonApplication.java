package com.pergamon.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.pergamon")
public class PergamonApplication {
	
	public static void main(String[] args) {
		
		SpringApplication.run(PergamonApplication.class, args);
		
	}

}
