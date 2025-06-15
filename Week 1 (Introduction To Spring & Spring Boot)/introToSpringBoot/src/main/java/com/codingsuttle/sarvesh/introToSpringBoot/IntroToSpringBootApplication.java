package com.codingsuttle.sarvesh.introToSpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Starting point of SpringBoot application
@SpringBootApplication
public class IntroToSpringBootApplication implements CommandLineRunner {

	@Autowired //Notifies that Bean has been used
	Apple apple1;

	@Autowired //Notifies that Bean has been used
	Apple apple2;

	public static void main(String[] args) {
		SpringApplication.run(IntroToSpringBootApplication.class, args);
		// Normal way
		Apple obj = new Apple();
		obj.eatApple();


	}

	@Override
	public void run(String... args) throws Exception {
		apple1.eatApple();
		apple2.eatApple();
		System.out.println(apple1.hashCode());
		System.out.println(apple2.hashCode());
	}

}
