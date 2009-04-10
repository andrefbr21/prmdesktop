package com.agnaldo4j.bo.pessoa;

import java.io.File;
import java.net.URI;

public class PessoaFisica extends Pessoa {
    
    private static final long serialVersionUID = 1L;
    private float peso, altura;
    private String sexo, estadoCivil, foto;
    private transient URI uri;
    
    public PessoaFisica(){}
    
    public float getAltura() {
        return altura;
    }
    
    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    public String getEstadoCivil() {
        return estadoCivil;
    }
    
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    public float getPeso() {
        return peso;
    }
    
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    public String getSexo() {
        return sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public URI getFoto() {
        if (this.uri == null) this.uri = new File(this.foto).toURI();
        return this.uri;
    }
    
    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    public String toString() {
        return super.toString() + " peso: "+ this.peso+" altura: "+this.altura+" sexo: "+this.sexo+" estadoCivil: "+this.estadoCivil;
    }
}