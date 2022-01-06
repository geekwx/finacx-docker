package br.com.geekwx.finacx.dto;

import java.util.List;

import br.com.geekwx.finacx.models.Permissao;
import br.com.geekwx.finacx.models.Usuario;

public class UsuarioViewDto {

	
	private Long id;
	private String nome;
	private String username;
	private Boolean contaNaoExpirada;
	private Boolean contaNaoBloqueada;
	private Boolean credencialNaoExpirada;
	private Boolean ativo;	
	private List<Permissao> permissoes ;
	
	
	
	public List<Permissao> getPermissoes() {
		return permissoes;
	}
	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getContaNaoExpirada() {
		return contaNaoExpirada;
	}
	public void setContaNaoExpirada(Boolean contaNaoExpirada) {
		this.contaNaoExpirada = contaNaoExpirada;
	}
	public Boolean getContaNaoBloqueada() {
		return contaNaoBloqueada;
	}
	public void setContaNaoBloqueada(Boolean contaNaoBloqueada) {
		this.contaNaoBloqueada = contaNaoBloqueada;
	}
	public Boolean getCredencialNaoExpirada() {
		return credencialNaoExpirada;
	}
	public void setCredencialNaoExpirada(Boolean credencialNaoExpirada) {
		this.credencialNaoExpirada = credencialNaoExpirada;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	
	static public UsuarioViewDto conversor(Usuario usuario) {
		
		UsuarioViewDto usuarioView = new UsuarioViewDto();
		usuarioView.setAtivo(usuario.getEnabled());
		usuarioView.setContaNaoBloqueada(usuario.getAccountNonLocked());
		usuarioView.setContaNaoExpirada(usuario.getAccountNonExpired());
		usuarioView.setCredencialNaoExpirada(usuario.getCredentialsNonExpired());
		usuarioView.setId(usuario.getId());
		usuarioView.setNome(usuario.getNome());
		usuarioView.setUsername(usuario.getNome());
		usuarioView.setPermissoes(usuario.getPermissoes());
		
		return usuarioView;
		
	}
	
	

}
