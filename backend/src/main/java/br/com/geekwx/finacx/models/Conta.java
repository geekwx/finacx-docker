package br.com.geekwx.finacx.models;

import java.math.BigDecimal;
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
 * Class para cuidar do modelo de conta no banco
 * 
 * */


@Entity
@Table(name = "contas")
public class Conta {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String nome;
	private BigDecimal saldo;
	
	/*
	 * Especial usuario 
	 * */
	@ManyToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@Override
	public String toString() {
		return "Conta [id=" + id + ", nome=" + nome + ", saldo=" + saldo + "]";
	}
	@JsonIgnore
	@OneToMany(mappedBy = "conta")
	private List<Movimento> movimento;
	
	
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
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	public void retiraSaldo(BigDecimal valor) {
		this.saldo = this.saldo.subtract(valor);
	}
	public void adicionaSaldo(BigDecimal valor) {
		this.saldo = this.saldo.add(valor);
	}
	public void transfereValor(BigDecimal valor, Conta conta) {
		this.retiraSaldo(valor);
		conta.adicionaSaldo(valor);
	}
}
