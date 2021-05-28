package com.ramvp.blogPessoal.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramvp.blogPessoal.model.Postagem;

@Repository
public interface Postagemrepository extends JpaRepository<Postagem, Long>{
	
	public Optional<Postagem> findByTituloLike(String titulo);
	
	public List<Postagem> findAllByTexto(String texto);
}
