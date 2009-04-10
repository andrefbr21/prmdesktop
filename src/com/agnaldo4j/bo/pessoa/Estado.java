package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public class Estado extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private String nome, sigla;
	private Pais pais;
	
	public Estado() {}

	public String getNome() {
		return nome;
	}

	public void setNome(String descicao) {
		this.nome = descicao;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais meuPais) {
		this.pais = meuPais;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
}
