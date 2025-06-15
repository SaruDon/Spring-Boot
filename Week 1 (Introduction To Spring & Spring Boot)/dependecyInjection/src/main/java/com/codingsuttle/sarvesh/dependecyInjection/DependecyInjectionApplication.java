package com.codingsuttle.sarvesh.dependecyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DependecyInjectionApplication implements CommandLineRunner {


	@Autowired
	DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(DependecyInjectionApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(dbService.getData());
	}
}
