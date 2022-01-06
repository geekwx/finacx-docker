package br.com.geekwx.finacx.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;





/*
 * Class para cuidar do modelo de categoria, no programa 
 * sera separado por categorias
 * 
 * */


@Entity
@Table(name = "categorias")
public class Categoria {
	
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	@NotBlank
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Movimento> movimento;
	
	
	/*
	 * Especial usuario 
	 * */
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + "]";
	}
	public List<Movimento> getMovimento() {
		return movimento;
	}
	public void setMovimento(List<Movimento> movimento) {
		this.movimento = movimento;
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
	
	
	/*Funcao para salvar a Categoria de Transferencia de valor */
	static public Categoria categoriaTransferencia() {
		Categoria categoria = new Categoria();
		categoria.setId(Long.valueOf(1));
		categoria.setNome("Transferencia");
		return categoria;
	}
}
