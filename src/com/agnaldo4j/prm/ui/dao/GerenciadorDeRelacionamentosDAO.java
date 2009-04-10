package com.agnaldo4j.prm.ui.dao;

import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import java.util.Observer;

public interface GerenciadorDeRelacionamentosDAO {
    public void addObserver(Observer observer);
    public GerenciadorDeRelacionamentos getGerenciadorDeRelacionamentos();
    public void gravar();
}
