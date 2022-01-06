package br.com.geekwx.finacx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 
 * Classe para usuario pode fazer alteração propria, como trocar senha, 
 * nesse momento inicial irei apenas deixar trocar a senha, futuramente posso
 * adicionar outros
 * 
 * */

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	
	

/*
 * Parte especial no qual o usuario irar trocar a sua propria senha
 * 
 * */
@GetMapping
public ResponseEntity<?> alterarSenha(){
	return ResponseEntity.ok().body("Senha alterada com sucesso");
	
}
	

}
