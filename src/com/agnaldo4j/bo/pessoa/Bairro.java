package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public class Bairro extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private String nome;
	private Cidade cidade;
	public Bairro() {}
	
	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
