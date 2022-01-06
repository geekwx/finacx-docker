package br.com.geekwx.finacx.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geekwx.finacx.dto.UsuarioDto;
import br.com.geekwx.finacx.dto.UsuarioViewDto;
import br.com.geekwx.finacx.models.Movimento;
import br.com.geekwx.finacx.models.Permissao;
import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.repository.PermissaoRepository;
import br.com.geekwx.finacx.repository.UsuarioRepository;
import br.com.geekwx.finacx.utils.GeraSenha;

/*
 * Controller se usuario, somente admnin e manager poderam utilizar essas rotas
 * adicao de novo usuario, exclusao, e permissao de usuario
 * */
@RestController
@RequestMapping("/admin/usuario")
public class AdminController {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	PermissaoRepository permissaoRepository;
	
	/* Rota para pegar a lista de usuario */
	@GetMapping
	public List<UsuarioViewDto> findAll() {
		List <Usuario> listaUsuario = repository.findAll();
		List <UsuarioViewDto> listaUsuarioView = new ArrayList<UsuarioViewDto>();
		for (Usuario usuario : listaUsuario) {
			UsuarioViewDto usuarioView = UsuarioViewDto.conversor(usuario);
			listaUsuarioView.add(usuarioView);
		}
		
		
		return (List<UsuarioViewDto>) listaUsuarioView; 
 
	}

	/*Buscando por usuario id */
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<UsuarioViewDto> findOne(@PathVariable Long id) {
		Optional<Usuario> optional = repository.findById(id); 
		if(optional.isPresent()) {
			UsuarioViewDto usuarioView = UsuarioViewDto.conversor(optional.get());
			return ResponseEntity.ok().body(usuarioView);
		}
		return ResponseEntity.notFound().build();
	}


/*
 * Criando no banco um usuario novo
 * */
@PostMapping
public ResponseEntity<?> create( @RequestBody @Valid UsuarioDto usuarioDto) {
	
	
	
	Optional<Permissao> optionalPermissao = permissaoRepository.findById(usuarioDto.getPermissaoId());
	if(optionalPermissao == null ) {
		 return ResponseEntity.badRequest().body("Nao possui essa permissão");
	 } 
	Usuario usuario = usuarioDto.criarUsuario(optionalPermissao.get());
	
	
	//.body(repository.save(usuario))
	return ResponseEntity.ok().body(repository.save(usuario));
			
}

@DeleteMapping("/{id}")
public ResponseEntity<?> remover(@PathVariable Long id){
	Optional<Usuario> optional = repository.findById(id);
	if ( optional.isPresent()) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	return ResponseEntity.notFound().build();
	
}



/*
 * Realizar atualização e trocar de informação de usuario
 * */
@PutMapping(path= "/{id}")
public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody UsuarioDto usuarioDto){
	
	Optional<Permissao> optionalPermissao = permissaoRepository.findById(usuarioDto.getPermissaoId());
	if(optionalPermissao == null ) {
		 return ResponseEntity.badRequest().body("Nao possui essa permissão");
	 } 
	
	//Buscando no banco o usuario
	Optional<Usuario> optional = repository.findById(id);
	if(optional.isPresent()) {
		Usuario usuario = usuarioDto.updateUsuario(optional.get(), usuarioDto.criarUsuario(optionalPermissao.get()));
		return ResponseEntity.ok().body(repository.save(usuario));
	}
	
	return ResponseEntity.notFound().build();
}


@PostMapping(path= "/{id}/alterasenha")
public ResponseEntity<?> updateSenha( @PathVariable("id") Long id,  @RequestBody @Valid String senha ) {
	
	Optional<Usuario> optional = repository.findById(id); 
	if(optional.isPresent()) {
		Usuario usuario = optional.get();
		usuario.setSenha(GeraSenha.cria(senha));
		return ResponseEntity.ok().body("Senha alterada com sucesso");
	}
	return ResponseEntity.notFound().build();
	
	
			
}



}






