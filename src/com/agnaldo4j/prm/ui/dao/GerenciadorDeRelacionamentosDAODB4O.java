package com.agnaldo4j.prm.ui.dao;

import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import com.db4o.ObjectContainer;
import java.util.Observable;

public class GerenciadorDeRelacionamentosDAODB4O extends Observable implements GerenciadorDeRelacionamentosDAO {
    
    private final ObjectContainer bancoDeObjetos;
    private GerenciadorDeRelacionamentos gerenciador;
    
    public GerenciadorDeRelacionamentosDAODB4O(ObjectContainer bancoDeObjetos, GerenciadorDeRelacionamentos gerenciador) {
        this.bancoDeObjetos = bancoDeObjetos;
        this.gerenciador = gerenciador;
    }
    
    public GerenciadorDeRelacionamentos getGerenciadorDeRelacionamentos() {
        return this.gerenciador;
    }
    
    public void gravar() {
        this.bancoDeObjetos.set(this.gerenciador);
        this.setChanged();
        this.notifyObservers(this.gerenciador);
    }
}