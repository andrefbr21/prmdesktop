package com.agnaldo4j.bo.pessoa;

import java.util.Map;

import com.agnaldo4j.bo.ObjetoDeNegocioImpl;

public class Documento extends ObjetoDeNegocioImpl {
	
    private static final long serialVersionUID = 1L;
    private String numero, tipoDocumento;
    
    public Documento() {}

    public String getNumero() {
            return numero;
    }

    public void setNumero(String numero) {
            this.numero = numero;
    }

    public String getTipoDocumento() {
            return tipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
    }
    
    public String toString() {
        return this.tipoDocumento + " - " + this.numero;
    }
}
