package com.agnaldo4j.bo.crm;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;
import com.agnaldo4j.bo.pessoa.Pessoa;
import java.util.HashSet;
import java.util.Set;

public class GerenciadorDeRelacionamentos extends ObjetoDeNegocioImpl {
    
    private static final long serialVersionUID = 1L;
    private Pessoa pessoa;
    private Set<GrupoDeRelacionamentos> gruposDeRelacionamentos;
    
    public GerenciadorDeRelacionamentos(){
        this.gruposDeRelacionamentos = new HashSet<GrupoDeRelacionamentos>();
    }
    
    public GerenciadorDeRelacionamentos(Pessoa pessoa, Set<GrupoDeRelacionamentos> grupos) {
        this.pessoa = pessoa;
        this.gruposDeRelacionamentos = grupos;
    }
    
    public Pessoa getPessoa() {
        return pessoa;
    }
    
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
    
    public void removeGrupoDeRelacionamentos(GrupoDeRelacionamentos grupo) {
        this.gruposDeRelacionamentos.remove(grupo);
    }
    
    public void addGrupoDeRelacionamentos(GrupoDeRelacionamentos grupo) {
        this.gruposDeRelacionamentos.add(grupo);
    }
    
    
    public Set<GrupoDeRelacionamentos> getGruposDeRelacionamentos() {
        return gruposDeRelacionamentos;
    }
    
    public void setGruposDeRelacionamentos(Set<GrupoDeRelacionamentos> grupos) {
        this.gruposDeRelacionamentos = grupos;
    }
    
    public String toString() {
        return "Relacionamentos";
    }
}