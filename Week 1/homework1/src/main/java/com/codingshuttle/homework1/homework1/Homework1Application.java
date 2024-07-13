package com.codingshuttle.homework1.homework1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Homework1Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Homework1Application.class, args);
	}

	@Autowired
	CakeBaker ck;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(ck.bakeCake());
	}
}
