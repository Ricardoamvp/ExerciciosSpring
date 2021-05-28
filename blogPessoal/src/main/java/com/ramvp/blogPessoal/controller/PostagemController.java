package com.ramvp.blogPessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramvp.blogPessoal.model.Postagem;
import com.ramvp.blogPessoal.repository.Postagemrepository;
import com.ramvp.blogPessoal.services.PostagemServices;


@RestController
@RequestMapping("/postagens")
@CrossOrigin("*")
public class PostagemController {
	
	@Autowired
	public Postagemrepository repository;
	@Autowired
	public PostagemServices service;
	
	@GetMapping	
	public List<Postagem> findAllPostagens() {
		return repository.findAll();
	}
	
	@GetMapping("/porid/{id}")
	public ResponseEntity<?> findByIDPostagem(@PathVariable Long id) {
		Optional<Postagem> resultado = repository.findById(id);
		if(resultado.isPresent()) {
			return ResponseEntity.status(HttpStatus.FOUND).body(resultado);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
		}
	}
	
	@GetMapping("/portitulo/{titulo}")
	public ResponseEntity<Optional<Postagem>> findByTitulo(@PathVariable String titulo) {
		return ResponseEntity.status(202).body(repository.findByTituloLike(titulo));
	}
	
	@PostMapping("/salvar")
	public ResponseEntity<Postagem> salvarPostagem(@Valid @RequestBody Postagem novaPostagem) {
		Optional<Postagem> opt = service.salvarPostagem(novaPostagem);
		if(opt.isEmpty()){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(novaPostagem);
		}
	}
	
	@GetMapping("/portexto")
	public List<Postagem> buscarPorTexto(@RequestParam String texto) {
		return repository.findAllByTexto(texto);
	}
	
	@PutMapping("/atualizar/{id_postagem}")
	public ResponseEntity<Postagem> atualizaPostagem (@PathVariable Long id_postagem, @RequestBody Postagem atualizaPostagem) {
		return service.atualizarPostagem(id_postagem,atualizaPostagem)
		.map(postagemAtualizada -> ResponseEntity.status(HttpStatus.ACCEPTED).body(postagemAtualizada))
		.orElse(ResponseEntity.status(304).build());
	}
	
	@DeleteMapping("/deletar/{id_postagem}")
	public ResponseEntity<String> deletaPostagem (@PathVariable Long id_postagem) {
		
		if(repository.findById(id_postagem).isPresent()){
		repository.deleteById(id_postagem);
		return ResponseEntity.status(HttpStatus.OK).body("Postagem Deletada");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Postagem não encontrada");
		}
	}
}