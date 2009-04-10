package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public class Telefone extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private String numero, tipoTelefone;
	private boolean recado, comercial, pessoal;

	public Telefone() {}	
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

        public String getTipoTelefone() {
            return this.tipoTelefone;
        }

        public void setTipoTelefone(String tipoTelefone) {
            this.tipoTelefone = tipoTelefone;
        }

        public boolean getEhParaRecado() {
            return this.recado;
        }

        public void setEhParaRecado(boolean recado) {
            this.recado = recado;
        }

        public boolean getEhComercial() {
            return this.comercial;
        }

        public void setEhComercial(boolean comercial) {
            this.comercial = comercial;
        }

        public boolean getEhPessoal() {
            return this.pessoal;
        }

        public void setEhPessoal(boolean pessoal) {
            this.pessoal = pessoal;
        }
}
