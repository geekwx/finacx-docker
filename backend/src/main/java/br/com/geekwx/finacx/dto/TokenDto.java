package br.com.geekwx.finacx.dto;

import java.util.List;

import br.com.geekwx.finacx.models.Permissao;
import br.com.geekwx.finacx.models.Usuario;

public class TokenDto {
	
	private String token;
	private String tipo;
	private String username;
	private List<Permissao> permissoes;
	
	
	public TokenDto(String token, String tipo, Usuario usuario) {
		this.token = token;
		this.tipo = tipo;
		this.username = usuario.getUsername();
		this.permissoes = usuario.getPermissoes();
	}
	public String getUsername() {
		return username;
	}
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public String getToken() {
		return token;
	}
	public String getTipo() {
		return tipo;
	}
	
	

}
