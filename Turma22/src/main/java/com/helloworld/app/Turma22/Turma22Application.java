package com.helloworld.app.Turma22;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Turma22Application {

	@GetMapping
	public String metodoBatatinha() {
		return "Olá Mundo, Turma 22";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Turma22Application.class, args);
	}

}
