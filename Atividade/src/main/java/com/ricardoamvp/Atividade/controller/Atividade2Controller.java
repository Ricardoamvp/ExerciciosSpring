package com.ricardoamvp.Atividade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade2")
public class Atividade2Controller {
	
	@GetMapping
	public String atividade2() {
		return "\tAtividade 2 \nAprender Spring Boot";
	}

}
