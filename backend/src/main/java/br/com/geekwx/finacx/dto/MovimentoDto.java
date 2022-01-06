package br.com.geekwx.finacx.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import br.com.geekwx.finacx.models.Categoria;
import br.com.geekwx.finacx.models.Conta;
import br.com.geekwx.finacx.models.Movimento;
import br.com.geekwx.finacx.models.Tipo;



public class MovimentoDto {
	
	@NotNull
	private String descricao;
	
	@Max(2)
	private Integer tipo;
	
	
	private BigDecimal valor;
	
	private Long categoriaId;
	private Long contaId;
	
	
	public Long getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Long categoriaId) {
		this.categoriaId = categoriaId;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	

	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	private String dataCriacao;
	
	
	
	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	LocalDate converterData(String dataCriacao){
		if(dataCriacao == null) {
			return LocalDate.now();
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataCriacao, formatter);
		return data;
	}
	
	public Movimento criarMovimento(Categoria categoria, Conta conta) {
		
	
		 
		Movimento movimento = new Movimento();
		
	
			
			//Definindo tipo 
			if(this.tipo.equals(0)) {	
						
				movimento.setTipo(Tipo.DESPESA);
			}
			if(this.tipo.equals(1)) {
				movimento.setTipo(Tipo.RECEITA);
			}
			if(this.tipo.equals(2)) {
				movimento.setTipo(Tipo.TRANSFERENCIA);
			}
			
			movimento.setDescricao(this.descricao);
			
			if(this.valor == null) {
				movimento.setValor(BigDecimal.ZERO);
			} else {
				movimento.setValor(this.valor);
			}
			movimento.setDataCriacao(converterData(this.dataCriacao));
			movimento.setConta(conta);
			movimento.setCategoria(categoria);
//			conta.retiraSaldo(movimento.getValor());
			tipoMovimento(movimento.getTipo(), conta, movimento.getValor());
			return movimento;
			
	}
	


	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/*
	 * Funcao para comparar movimentos e atualizar o movimento.
	 * 
	 * */
	public Movimento updateMovimento(Movimento movimento, Movimento novoMovimento) {
		if(novoMovimento.equals(movimento)) {
			return movimento;
		}
		if(!novoMovimento.getCategoria().equals(null)|| !novoMovimento.getCategoria().equals(movimento.getCategoria()) ) {
			movimento.setCategoria(novoMovimento.getCategoria());
		}
		if(!novoMovimento.getConta().equals(null) || !novoMovimento.getConta().equals(movimento.getConta()) ) {
			movimento.setConta(novoMovimento.getConta());
		}
		if(!novoMovimento.getDataCriacao().equals(null) || !novoMovimento.getDataCriacao().equals(movimento.getDataCriacao()) ) {
			movimento.setDataCriacao(novoMovimento.getDataCriacao());
		}
		if(!novoMovimento.getDescricao().equals(null) || !novoMovimento.getDataCriacao().equals(movimento.getDataCriacao()) ) {
			movimento.setDataCriacao(novoMovimento.getDataCriacao());
		}
		if(!novoMovimento.getValor().equals(null) || !novoMovimento.getValor().equals( movimento.getValor()) ) {
			movimento.setValor(novoMovimento.getValor());
		}
		if(!novoMovimento.getTipo().equals(null) || !novoMovimento.getTipo().equals(movimento.getTipo()) ) {
			movimento.setTipo(novoMovimento.getTipo());
		}
		
		return movimento;
		
	}
	
	public void tipoMovimento(Tipo tipo, Conta conta, BigDecimal valor) {
		if(tipo.equals(Tipo.DESPESA)) {
			conta.retiraSaldo(valor);
		}
		if(tipo.equals(Tipo.RECEITA)) {
			conta.adicionaSaldo(valor);
		}
		
	}
	

		
}

