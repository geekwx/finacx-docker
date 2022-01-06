package br.com.geekwx.finacx.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
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

import br.com.geekwx.finacx.dto.ValorDto;
import br.com.geekwx.finacx.models.Categoria;
import br.com.geekwx.finacx.models.Conta;
import br.com.geekwx.finacx.models.Movimento;
import br.com.geekwx.finacx.models.Tipo;
import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.repository.CategoriaRepository;
import br.com.geekwx.finacx.repository.ContaRepository;
import br.com.geekwx.finacx.repository.MovimentoRepository;

/*
 * Controller da Conta
 * 
 * Adicionar novas Conta
 * Atualizar
 * Deletar
 * visualizar todas
 * visualizar unica
 * 
 * */


@RestController
@RequestMapping("/conta")
public class ContaController {

	@Autowired
	ContaRepository repository;
	
	@Autowired
	MovimentoRepository movimentoRepository;
	
	
	/*Rota para Buscar uma lista de categoria*/
	@GetMapping
	public List<Conta> findAll(@AuthenticationPrincipal Usuario usuario) {
		return (List<Conta>) repository.findAllByUsuario(usuario); 
	}
	
	/* Busca por id*/
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Conta> findOne(@PathVariable Long id) {
		Optional<Conta> optional = repository.findById(id); 
		if(optional.isPresent()) {
			return ResponseEntity.ok().body(optional.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	
	/*cria no banco de dados */
	@PostMapping
	public ResponseEntity<Conta> create( @RequestBody @Valid Conta conta, 
			@AuthenticationPrincipal Usuario usuario) {
		if(conta.getSaldo() == null) {
			conta.setSaldo(BigDecimal.ZERO);
		}
		conta.setUsuario(usuario);
		return ResponseEntity.ok().body(repository.save(conta));
				
	}
	
	/* atualiza no banco de dados */
	@PutMapping(path = {"/{id}"})
	public ResponseEntity<Conta> update( @PathVariable("id") Long id, 
			@RequestBody Conta conta) {
		Optional<Conta> optional =  repository.findById(id);
		if (optional.isPresent()) {
			 Conta contaAtualizada =  optional.get();
			 contaAtualizada.setNome(conta.getNome());
			 contaAtualizada.setSaldo(conta.getSaldo());
			 return ResponseEntity.ok().body(repository.save(contaAtualizada));
		}
		
		return ResponseEntity.notFound().build();
	}

	/* Rota deletando o item no sistema*/
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> remover(@PathVariable Long id){
		Optional<Conta> optional = repository.findById(id);
		if ( optional.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
		
	}

	/* Criando a transferencia de valores entre contas*/
	
	/* atualiza no banco de dados */
	@PostMapping(path = {"/transfere/{contaId}/{contaId2}"},  consumes={"application/json"})
	public ResponseEntity<?> transfereSaldoConta( @PathVariable("contaId") Long contaId,
			@PathVariable("contaId2") Long contaId2, @RequestBody ValorDto valorDto
			) {
		
		;
		
		System.out.println(contaId);
		System.out.println(contaId2);
		System.out.println(valorDto.getValor());
		
		Optional<Conta> optionalConta =  repository.findById(contaId);
		Optional<Conta> optionalConta2 =  repository.findById(contaId2);
		

		
		if (optionalConta.isPresent() && optionalConta2.isPresent()) {
			
			 Conta conta1 = optionalConta.get();
			 Conta conta2 = optionalConta2.get();
			
			 conta1.transfereValor(valorDto.getValor(), conta2);
			 
			 Movimento movimento = geraMovimento(conta1, conta2, valorDto.getValor());
			
			 return ResponseEntity.ok().body(movimentoRepository.save(movimento));
			

		}
		
		return ResponseEntity.ok().body("Teste 2 =D");
	}
	
	/*Funcao para gerar um movimento de trasferencia*/
	private Movimento geraMovimento(Conta conta1, Conta conta2, BigDecimal valor) {
		
		
		 Movimento movimento = new Movimento();
		movimento.setDescricao("Conta "+ conta1.getNome()
		+ " transfere para Conta "+ conta2.getNome());
		movimento.setTipo(Tipo.TRANSFERENCIA);
		movimento.setValor(valor);
		movimento.setDataCriacao(LocalDate.now());
		movimento.setCategoria(Categoria.categoriaTransferencia());
		movimento.setConta(conta1);
		
		return movimento;
	}
	}
	
	

