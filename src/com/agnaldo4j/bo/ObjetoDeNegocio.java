package com.agnaldo4j.bo;

import java.io.Serializable;
import java.util.Date;

public interface ObjetoDeNegocio extends Serializable {
    public Date getDataCadastro();
    public void setDataCadastro(Date dataCadastro);
}