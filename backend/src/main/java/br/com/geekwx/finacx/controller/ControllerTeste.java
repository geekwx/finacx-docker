package br.com.geekwx.finacx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerTeste {

	
	@GetMapping("/t")
	public String hello() {
		String mensagem = "Hello World!!! =D";
		return mensagem;
	}
	
	@PostMapping("/t")
	public String postHello(@RequestBody String name  ) {
		String mensagem = "35" + name;
		return mensagem;
	}
}
