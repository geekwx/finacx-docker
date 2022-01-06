package br.com.geekwx.finacx.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.geekwx.finacx.models.Permissao;
import br.com.geekwx.finacx.models.Usuario;
import br.com.geekwx.finacx.utils.GeraSenha;

public class UsuarioDto {
	
	private String nome;
	private String username;
	private String senha;
	private Boolean contaNaoExpirada;
	private Boolean contaNaoBloqueada;
	private Boolean credencialNaoExpirada;
	private Boolean ativo;
	
	//puxando o id da permissao
	
	private Long permissaoId;

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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public Long getPermissaoId() {
		return permissaoId;
	}

	public void setPermissaoId(Long permissaoId) {
		this.permissaoId = permissaoId;
	}
	
/*
 * Metodo para a criação de usuario, usando o esquema para adicionar parte a parte
 * 
 * */
	
	public Usuario criarUsuario(Permissao permissao ) {
		
		Usuario usuario = new Usuario();
		String senhaCryptada = GeraSenha.cria(this.senha); 
				
		
		
		List<Permissao>  permissoes= new ArrayList<Permissao>();
		permissoes.add(permissao);
		
		usuario.setNome(this.nome);
		usuario.setSenha(senhaCryptada);
		usuario.setUsername(this.username);
		usuario.setAccountNonExpired(this.contaNaoExpirada);
		usuario.setAccountNonLocked(this.contaNaoBloqueada);
		usuario.setCredentialsNonExpired(this.credencialNaoExpirada);
		usuario.setEnabled(this.ativo);
		usuario.setPermissoes(permissoes);
		return usuario;
		
		
		
	}
	/*
	 * Metodo para realizar o updade do usuario
	 * */
	
	public Usuario updateUsuario(Usuario usuario, Usuario novoUsuario) {
		if(novoUsuario.equals(usuario)) {
			return usuario;
		}
		if(!novoUsuario.getAccountNonExpired().equals(usuario.getAccountNonExpired())) {
			usuario.setAccountNonExpired(novoUsuario.getAccountNonExpired());
		}
		if(!novoUsuario.getAccountNonLocked().equals(usuario.getAccountNonLocked())) {
			usuario.setAccountNonLocked(novoUsuario.getAccountNonLocked());
		}
		if(!novoUsuario.getCredentialsNonExpired().equals(usuario.getCredentialsNonExpired())) {
			usuario.setCredentialsNonExpired(novoUsuario.getCredentialsNonExpired());
		}
		if(!novoUsuario.getEnabled().equals(usuario.getEnabled())) {
			usuario.setEnabled(novoUsuario.getEnabled());
		}
				
		if(!novoUsuario.getPermissoes().equals(usuario.getPermissoes())) {
			usuario.setPermissoes(novoUsuario.getPermissoes());
		}
		if(!novoUsuario.getUsername().equals(usuario.getUsername())) {
			usuario.setUsername(novoUsuario.getUsername());
		}
		if(!novoUsuario.getNome().equals(usuario.getNome())) {
			usuario.setNome(novoUsuario.getNome());
		}
//		if(!novoUsuario.getSenha().equals(usuario.getSenha())) {
//			usuario.setSenha(novoUsuario.getSenha());
//		}
		return usuario;
		
	}
	
//	 static public String geraSenha(String senha) {
//		String password = senha;
//		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
//		String encodedPassword = passwordEncoder.encode(password);
//		return encodedPassword;
//	}
	

}
