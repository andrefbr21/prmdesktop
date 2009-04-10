package com.agnaldo4j.bo.pessoa;

import java.util.Map;

public class PessoaJuridica extends Pessoa {
	
	private static final long serialVersionUID = 1L;
	private double faturamento;
	private long numeroDeSites, numeroDeFuncionarios;
	
	public PessoaJuridica(){}
	
	public double getFaturamento() {
		return faturamento;
	}

	public void setFaturamento(double faturamento) {
		this.faturamento = faturamento;
	}

	public long getNumeroDeFuncionarios() {
		return numeroDeFuncionarios;
	}

	public void setNumeroDeFuncionarios(long funcionarios) {
		this.numeroDeFuncionarios = funcionarios;
	}

	public long getNumeroDeSites() {
		return numeroDeSites;
	}

	public void setNumeroDeSites(long sites) {
		this.numeroDeSites = sites;
	}
}
