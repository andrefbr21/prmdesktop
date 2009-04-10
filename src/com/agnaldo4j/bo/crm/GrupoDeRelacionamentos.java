package com.agnaldo4j.bo.crm;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;
import java.util.HashSet;
import java.util.Set;

public class GrupoDeRelacionamentos extends ObjetoDeNegocioImpl  {
    
    private String nome;
    private Set<Relacionamento> relacionamentos;
    
    public GrupoDeRelacionamentos() {
        this.relacionamentos = new HashSet<Relacionamento>();
    }
    
    public GrupoDeRelacionamentos(String nome, Set<Relacionamento> relacionamentos) {
        this.nome = nome;
        this.relacionamentos = relacionamentos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void removeRelacionamento(Relacionamento relacionamento) {
        this.relacionamentos.remove(relacionamento);
    }
    
    public void addRelacionamento(Relacionamento relacionamento) {
        this.relacionamentos.add(relacionamento);
    }
    
    public Set<Relacionamento> getRelacionamentos() {
        return relacionamentos;
    }

    public void setRelacionamentos(Set<Relacionamento> relacionamentos) {
        this.relacionamentos = relacionamentos;
    }
    
    public String toString() {
        return this.nome;
    }
}
