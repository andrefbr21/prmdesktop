package com.agnaldo4j.bo.pessoa;

import java.util.Map;

public class Logradouro extends Endereco {
	
	private static final long serialVersionUID = 1L;
	private Bairro bairro;
	private String numero, complemento;
	private CEP cep;
        private boolean usoCorrespondencia;

	public Logradouro() {}
	
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro meuBairro) {
		this.bairro = meuBairro;
	}

	public CEP getCep() {
		return cep;
	}

	public void setCep(CEP meuCep) {
		this.cep = meuCep;
	}

        public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
        
        public boolean isUsoCorrespondecia() {
            return this.usoCorrespondencia;
        }

        public void setUsoCorrespondecia(boolean usoCorrespondencia) {
            this.usoCorrespondencia = usoCorrespondencia;
        }
}
