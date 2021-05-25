package com.ricardoamvp.Atividade.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/atividade1")
public class AtividadeController {
	@GetMapping
	public String atividade1() {
		return "\tAtividade 1 \nOrientação aos detalhes \nPersistência";
	}

}
