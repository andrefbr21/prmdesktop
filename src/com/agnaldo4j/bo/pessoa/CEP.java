package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public class CEP extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private String numero;
	
	public CEP() {}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
