package br.com.sxsolucoes.financeiro.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 

@Entity
public class FluxoCaixa  {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String tipo;
	
	@Column
	private String Fluxo;


	/*Getters and Setters*/
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getFluxo() {
		return Fluxo;
	}

	public void setFluxo(String fluxo) {
		Fluxo = fluxo;
	}
	
	

}
