package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public class Cidade extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private Estado estado;
	
	public Cidade() {}	

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
