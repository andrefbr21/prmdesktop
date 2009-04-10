
package com.agnaldo4j.bo.pessoa;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public abstract class Pessoa extends ObjetoDeNegocioImpl {	
	
	private String nome, fantasia;	
	private Date dataNascimento;	
	private Set<Documento> meusDocumentos;
	private Set<Logradouro> meusEnderecos;
	private Set<EnderecoVirtual> meusEnderecosVirtuais;
	private Set<Telefone> meusTelefones;
	
	public Pessoa() {}	

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getFantasia() {
		return fantasia;
	}

	public void setFantasia(String fantasia) {
		this.fantasia = fantasia;
	}
	
	public Set<Documento> getMeusDocumentos() {
		if (this.meusDocumentos == null) this.meusDocumentos = new HashSet<Documento>();
		return meusDocumentos;
	}

	public void setMeusDocumentos(Set<Documento> meusDocumentos) {
		this.meusDocumentos = meusDocumentos;
	}
    
        public Set<Logradouro> getMeusEnderecos() {
            if(this.meusEnderecos == null) this.meusEnderecos = new HashSet<Logradouro>();
            return meusEnderecos;
        }

        public void setMeusEnderecos(Set<Logradouro> meusEnderecos) {
            this.meusEnderecos = meusEnderecos;
        }
    
        public Set<EnderecoVirtual> getMeusEnderecosVirtuais() {
            if (this.meusEnderecosVirtuais == null) this.meusEnderecosVirtuais = new HashSet<EnderecoVirtual>();
            return this.meusEnderecosVirtuais;
        }

        public void setMeusEnderecosVirtuais(Set<EnderecoVirtual> meusEnderecosVirtuais) {
            this.meusEnderecosVirtuais = meusEnderecosVirtuais;
        }

        public Set<Telefone> getMeusTelefones() {
            if (this.meusTelefones == null) this.meusTelefones = new HashSet<Telefone>();
            return this.meusTelefones;
        }
    
        public void setMeusTelefones(Set<Telefone> meusTelefones) {
            this.meusTelefones = meusTelefones;
        }

        public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public String toString() {
		return "nome: "+ this.getNome()+" fantasia: "+ this.getFantasia()+" dataNascimento: "+this.getDataNascimento();
	}
}