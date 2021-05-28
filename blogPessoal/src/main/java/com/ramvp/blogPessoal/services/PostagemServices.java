package com.ramvp.blogPessoal.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramvp.blogPessoal.model.Postagem;
import com.ramvp.blogPessoal.repository.Postagemrepository;

@Service
public class PostagemServices {
	
	private @Autowired Postagemrepository repository;
	
	
	
	public Optional<Postagem> salvarPostagem(Postagem novaPostagem) {
		Optional<Postagem> postagemExistente = repository.findByTituloLike(novaPostagem.getTitulo());
		
		if (postagemExistente.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.ofNullable(repository.save(novaPostagem));
		}
	}

	public Optional<Postagem> atualizarPostagem(Long id, Postagem atualizacaoPostagem) {
		Optional<Postagem> postagemExistente = repository.findById(id);

		if (postagemExistente.isPresent()) {
			postagemExistente.get().setTexto(atualizacaoPostagem.getTexto());
			postagemExistente.get().setTitulo(atualizacaoPostagem.getTitulo());
			return Optional.ofNullable(repository.save(postagemExistente.get()));
		} else {
			return Optional.empty();
		}
	}
	
}
