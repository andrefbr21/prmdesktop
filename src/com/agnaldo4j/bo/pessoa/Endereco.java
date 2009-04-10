package com.agnaldo4j.bo.pessoa;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public abstract class Endereco extends ObjetoDeNegocioImpl {	
    private String nome, tipoEndereco;
    private boolean pessoal, comercial;
	
    public Endereco() {}

    public String getNome() {
            return nome;
    }

    public void setNome(String descricao) {
            this.nome = descricao;
    }

    public String getTipoEndereco() {
        return this.tipoEndereco;
    }
    
    public void setTipoEndereco(String tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }
    
    public boolean getEhPessoal() {
        return this.pessoal;
    }
    
    public void setEhPessoal(boolean pessoal) {
        this.pessoal = pessoal;
    }
    
    public boolean getEhComercial() {
        return this.comercial;
    }
    
    public void setEhComercial(boolean comercial) {
        this.comercial = comercial;
    }
}