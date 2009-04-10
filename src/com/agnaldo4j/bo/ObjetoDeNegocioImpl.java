package com.agnaldo4j.bo;

import java.util.Date;

public abstract class ObjetoDeNegocioImpl implements ObjetoDeNegocio {

    private static final long serialVersionUID = 1L;

    private Date dataCadastro;

    public ObjetoDeNegocioImpl() {
        dataCadastro = new Date();
    }

    public Date getDataCadastro() {
            return this.dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
            this.dataCadastro = dataCadastro;

    }
}