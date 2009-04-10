package com.agnaldo4j.bo.crm;

import java.util.Date;
import java.util.Set;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;
import com.agnaldo4j.bo.pessoa.PessoaFisica;
import java.util.HashSet;

public class Relacionamento extends ObjetoDeNegocioImpl {
	
	private static final long serialVersionUID = 1L;
	private PessoaFisica pessoa;
	private Date dataConhecimento;
	private String tipoContato;
	private Set<Conversa> conversas;
	
	public Relacionamento() {
            this.conversas = new HashSet<Conversa>();
        }
        
        public Relacionamento(PessoaFisica pessoa, Date dataConhecimento, String tipoContato) {
            this.conversas = new HashSet<Conversa>();
            this.pessoa = pessoa;
            this.dataConhecimento = dataConhecimento;
            this.tipoContato = tipoContato;
        }
	
	public Date getDataConhecimento() {
		return dataConhecimento;
	}

	public void setDataConhecimento(Date dataConhecimento) {
		this.dataConhecimento = dataConhecimento;
	}
	
	public PessoaFisica getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaFisica pessoa) {
		this.pessoa = pessoa;
	}

	public String getTipoContato() {
		return tipoContato;
	}

	public void setTipoContato(String tipoContato) {
		this.tipoContato = tipoContato;
	}

        public void addConversa(Conversa conversa) {
            this.conversas.add(conversa);
        }
        
	public Set<Conversa> getConversas() {
		return conversas;
	}

	public void setConversas(Set<Conversa> conversa) {
		this.conversas = conversa;
	}
        
        public String toString() {
            return this.pessoa.getNome()+" - "+this.pessoa.getFantasia();
        }
}