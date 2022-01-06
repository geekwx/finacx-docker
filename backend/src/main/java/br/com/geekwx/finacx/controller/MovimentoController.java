package br.com.geekwx.finacx.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import br.com.geekwx.finacx.dto.MovimentoDto;
import br.com.geekwx.finacx.models.Categoria;
import br.com.geekwx.finacx.models.Conta;
import br.com.geekwx.finacx.models.Movimento;
import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.repository.CategoriaRepository;
import br.com.geekwx.finacx.repository.ContaRepository;
import br.com.geekwx.finacx.repository.MovimentoRepository;

/*
 * Controller para todas as movimentacao do sistema
 * 
 * 
 * */


@RestController
@RequestMapping("/movimento")
public class MovimentoController {
	
	
	@Autowired
	MovimentoRepository repository;
	
	@Autowired
	CategoriaRepository categoriaRepository;

	@Autowired
	ContaRepository contaRepository;
	
	
	/*Rota para Buscar uma lista de categoria*/
	@GetMapping
	public Page<Movimento> findAll(@PageableDefault(sort = "dataCriacao", direction = Direction.DESC, page = 0, size = 10)
			Pageable paginacao, @AuthenticationPrincipal Usuario usuario) {
		return (Page<Movimento>) repository.findAllByUsuario(usuario, paginacao); 
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Movimento> findOne(@PathVariable Long id) {
		Optional<Movimento> optional = repository.findById(id); 
		if(optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	/*cria no banco de dados */
	@PostMapping
	public ResponseEntity<?> create( @RequestBody @Valid MovimentoDto movimentoDto,
			@AuthenticationPrincipal Usuario usuario) {
		
		
		
		System.out.println(movimentoDto.getTipo());
		
		/* Logica para buscar as infos no banco*/
		
		 Optional<Categoria> optionalCategoria = categoriaRepository.findById(movimentoDto.getCategoriaId());
		 if(optionalCategoria == null ) {
			 return ResponseEntity.badRequest().body("Nao possui essa categoria");
		 } 
		 Optional<Conta> optionalConta = contaRepository.findById(movimentoDto.getContaId());
		if(optionalConta == null) {
			return ResponseEntity.badRequest().body("Nao possui essa Conta");
		}
		
		
		/*Criando movimento */
		Movimento movimento = movimentoDto.criarMovimento(optionalCategoria.get(),  optionalConta.get() );
		
		movimento.setUsuario(usuario);
		return ResponseEntity.ok().body(repository.save(movimento));
				
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Movimento> optional = repository.findById(id);
		if ( optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping(path = {"/{id}"})
	public ResponseEntity<?> update( @PathVariable("id") Long id, 
			@RequestBody MovimentoDto movimentoDto) {
		
		/* Logica para buscar as infos no banco*/
		
		 Optional<Categoria> optionalCategoria = categoriaRepository.findById(movimentoDto.getCategoriaId());
		 if(optionalCategoria == null ) {
			 return ResponseEntity.badRequest().body("Nao possui essa categoria");
		 } 
		 Optional<Conta> optionalConta = contaRepository.findById(movimentoDto.getContaId());
		if(optionalConta == null) {
			return ResponseEntity.badRequest().body("Nao possui essa Conta");
		}
		
		
		//Buscando movimento no banco
		Optional<Movimento> optional =  repository.findById(id);
		if (optional.isPresent()) {
			
			
			 Movimento movimento = movimentoDto.updateMovimento(optional.get(), 
					 movimentoDto.criarMovimento(optionalCategoria.get(), optionalConta.get()));
					 
			 return ResponseEntity.ok().body(repository.save(movimento));
		}
		
		return ResponseEntity.notFound().build();
	}
}
