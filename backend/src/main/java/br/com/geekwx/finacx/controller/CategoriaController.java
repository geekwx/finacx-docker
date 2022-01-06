package br.com.geekwx.finacx.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geekwx.finacx.models.Categoria;
import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.repository.CategoriaRepository;

/*
 * Controller da Categoria
 * 
 * Adicionar novas Categoria
 * Atualizar
 * Deletar
 * visualizar todas
 * visualizar unica
 * 
 * */


@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	CategoriaRepository repository;
	
	
	/*Rota para Buscar uma lista de categoria*/
	@GetMapping
	public List<Categoria> findAll(@AuthenticationPrincipal Usuario usuario) {
		return (List<Categoria>) repository.findAllByUsuario(usuario); 
	}
	
	/* Busca por id*/
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Categoria> findOne(@PathVariable Long id, @AuthenticationPrincipal Usuario logado) {
		System.out.println(logado.getNome());
		System.out.println(logado.getId());
		Optional<Categoria> optional = repository.findById(id); 
		if(optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/*cria no banco de dados */
	@PostMapping
	public ResponseEntity<Categoria> create( @RequestBody @Valid Categoria categoria,
			@AuthenticationPrincipal Usuario usuario) {
			categoria.setUsuario(usuario);
		return ResponseEntity.ok().body(repository.save(categoria));
				
	}
	
	/* atualiza no banco de dados */
	@PutMapping(path = {"/{id}"})
	public ResponseEntity<Categoria> update( @PathVariable("id") Long id, 
			@RequestBody Categoria categoria) {
		Optional<Categoria> optional =  repository.findById(id);
		if (optional.isPresent()) {
			 Categoria categoriaAtualizada =  optional.get();
			 categoriaAtualizada.setNome(categoria.getNome());
			 return ResponseEntity.ok().body(repository.save(categoriaAtualizada));
		}
		
		return ResponseEntity.notFound().build();
	}

	/* Rota deletando o item no sistema*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Categoria> optional = repository.findById(id);
		if ( optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}

	
	
	
		
	}
	
	

