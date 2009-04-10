package com.agnaldo4j.bo.crm;


import com.agnaldo4j.bo.ObjetoDeNegocioImpl;
import java.util.Date;

public class Acao extends ObjetoDeNegocioImpl {
    
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    
    public Acao() {}
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Date getDataInicio() {
        return dataInicio;
    }
    
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }
    
    public Date getDataFim() {
        return dataFim;
    }
    
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }
    
}