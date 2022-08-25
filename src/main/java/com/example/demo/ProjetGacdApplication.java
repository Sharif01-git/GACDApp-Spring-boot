package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetGacdApplication {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 8181 );
		SpringApplication.run(ProjetGacdApplication.class, args);
	}

}
