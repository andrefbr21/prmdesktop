package com.agnaldo4j.bo.crm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;
import com.agnaldo4j.bo.pessoa.PessoaFisica;
import java.net.URI;

public class Conversa extends ObjetoDeNegocioImpl {
    
    private static final long serialVersionUID = 1L;
    private PessoaFisica pessoa;
    private Date data;
    private String titulo;
    private String conteudo;
    private Set<Acao> acoes;
    
    public Conversa() {}
    
    public String getConteudo() {
        return conteudo;
    }
    
    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }
    
    public Set<Acao> getAcoes() {
        if (this.acoes == null) this.acoes = new HashSet<Acao>();
        return this.acoes;
    }
    
    public void setAcoes(Set<Acao> acoes) {
        this.acoes = acoes;
    }
    
    public Date getData() {
        return data;
    }
    
    public void setData(Date data) {
        this.data = data;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public PessoaFisica getPessoa() {
        return pessoa;
    }

    public void setPessoa(PessoaFisica pessoa) {
        this.pessoa = pessoa;
    }
    
    public String toString() {
        return this.titulo;
    }    
}