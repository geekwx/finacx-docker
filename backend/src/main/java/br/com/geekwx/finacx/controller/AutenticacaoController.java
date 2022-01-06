package br.com.geekwx.finacx.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.geekwx.finacx.controller.form.LoginForm;
import br.com.geekwx.finacx.dto.TokenDto;
import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.repository.UsuarioRepository;
import br.com.geekwx.finacx.security.TokenService;


@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	
	
//	@Qualifier("authenticationManagerBean")
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm form){
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		
		try {
			Authentication authentication = authManager.authenticate(dadosLogin);
			Optional<Usuario> usuario = usuarioRepository.findByUsername(form.getUsername());
			String token = tokenService.gerarToken(authentication);
			return  ResponseEntity.ok(new TokenDto(token, "Bearer", usuario.get()));
		} catch (AuthenticationException  e) {
			return ResponseEntity.badRequest().build();
		}
		
	}

}
