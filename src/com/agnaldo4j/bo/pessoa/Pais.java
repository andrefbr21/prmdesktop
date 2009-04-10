package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;


public class Pais extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private String nome, sigla;
	
	public Pais() {}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String descricao) {
		this.nome = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
