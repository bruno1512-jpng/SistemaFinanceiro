package br.com.sxsolucoes.financeiro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_caixa")
public class Caixa {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String data;
	
	@Column
	private String tipo;
	
	@Column
	private String fluxocaixa;
	
	
	@Column
	private String descricao;
	
	@Column
	private double valor;

	@Column
	private double saldo;
	
	@Column
	private String conta;

	/*Getters and Setters*/
	

	public Long getId() {
		return id;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public String getFluxocaixa() {
		return fluxocaixa;
	}

	public void setFluxocaixa(String fluxocaixa) {
		this.fluxocaixa = fluxocaixa;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	
	
	
	
	
	
	
	
	
}