package com.agnaldo4j.prm.main;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import com.agnaldo4j.bo.crm.GrupoDeRelacionamentos;
import com.agnaldo4j.bo.crm.Relacionamento;
import com.agnaldo4j.bo.pessoa.PessoaFisica;
import com.agnaldo4j.prm.ui.PRMDesktop;
import com.agnaldo4j.prm.ui.TelaCadastroDeAcaoImpl;
import com.agnaldo4j.prm.ui.TelaCadastroDeContatoImpl;
import com.agnaldo4j.prm.ui.TelaCadastroDeConversaImpl;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAO;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAODB4O;
import com.agnaldo4j.prm.ui.manager.DialogInstance;
import com.agnaldo4j.prm.ui.manager.WindowManager;
import com.agnaldo4j.prm.ui.manager.WindowManagerImpl;
import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Query;
import de.javasoft.plaf.synthetica.SyntheticaBlueIceLookAndFeel;
import javax.swing.UIManager;

public class Main {
    
    private ObjectContainer bancoDeObjetos;
    public Main() {
        
        this.iniciarBancoDeObjetos();
        
        try {
          SyntheticaBlueIceLookAndFeel syntheticaBlueIceLookAndFeel = new SyntheticaBlueIceLookAndFeel();
          UIManager.setLookAndFeel(syntheticaBlueIceLookAndFeel);
        } catch (Exception e) {
          e.printStackTrace();
        }
        
        bancoDeObjetos = Db4o.openFile("dancoDeObjetos.dat");
        GerenciadorDeRelacionamentos gerenciador = seNaoExistirCriaGerenciadorDeRelacionamentos();
        GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO = new GerenciadorDeRelacionamentosDAODB4O(bancoDeObjetos, gerenciador);
        
        PRMDesktop prmDesktop = new PRMDesktop(gerenciadorDeRelacionamentosDAO, this.criaTelasDoSistema(gerenciadorDeRelacionamentosDAO));
        prmDesktop.setVisible(true);
    }
    
    public GerenciadorDeRelacionamentos seNaoExistirCriaGerenciadorDeRelacionamentos() {
        GerenciadorDeRelacionamentos gerenciador = this.recuperaGerenciadorDeRelacionamentos();
        if (gerenciador == null) gerenciador = this.criaGerenciadorDeRelacionamentos();
        return gerenciador;
    }
    
    public GerenciadorDeRelacionamentos criaGerenciadorDeRelacionamentos() {
        GerenciadorDeRelacionamentos gerenciador = new GerenciadorDeRelacionamentos();
        this.bancoDeObjetos.set(gerenciador);
        return gerenciador;
    }
    
    public GerenciadorDeRelacionamentos recuperaGerenciadorDeRelacionamentos() {
        GerenciadorDeRelacionamentos gerenciador = null;
        Query query = bancoDeObjetos.query();
        query.constrain(GerenciadorDeRelacionamentos.class);
        ObjectSet resultado = query.execute();
        if (resultado.size() > 0) gerenciador = (GerenciadorDeRelacionamentos)resultado.get(0);
        return gerenciador;
    }
    
    public static void main(String args[]) {
        new Main();
    }    

    private void iniciarBancoDeObjetos() {
        Db4o.configure().generateUUIDs(Integer.MAX_VALUE);
        Db4o.configure().generateVersionNumbers(Integer.MAX_VALUE);
        Db4o.configure().objectClass(GerenciadorDeRelacionamentos.class).cascadeOnActivate(true);
        Db4o.configure().objectClass(GerenciadorDeRelacionamentos.class).cascadeOnUpdate(true);
        Db4o.configure().objectClass(GrupoDeRelacionamentos.class).cascadeOnActivate(true);
        Db4o.configure().objectClass(GrupoDeRelacionamentos.class).cascadeOnUpdate(true);
        Db4o.configure().objectClass(Relacionamento.class).cascadeOnActivate(true);
        Db4o.configure().objectClass(Relacionamento.class).cascadeOnUpdate(true);
        Db4o.configure().objectClass(Conversa.class).cascadeOnActivate(true);
        Db4o.configure().objectClass(Conversa.class).cascadeOnUpdate(true);
        Db4o.configure().objectClass(PessoaFisica.class).cascadeOnActivate(true);
        Db4o.configure().objectClass(PessoaFisica.class).cascadeOnUpdate(true);
    }

    private WindowManager criaTelasDoSistema(GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO) {
        WindowManager windowManager = new WindowManagerImpl();
        windowManager.addDialogInstance(DialogInstance.CadastroDeContato, new TelaCadastroDeContatoImpl(gerenciadorDeRelacionamentosDAO));
        windowManager.addDialogInstance(DialogInstance.CadastroDeConversa, new TelaCadastroDeConversaImpl(gerenciadorDeRelacionamentosDAO, windowManager));
        windowManager.addDialogInstance(DialogInstance.CadastroDeAcao, new TelaCadastroDeAcaoImpl(gerenciadorDeRelacionamentosDAO));
        return windowManager;
    }
}
