package com.agnaldo4j.prm.ui.tree;

import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import com.agnaldo4j.bo.crm.GrupoDeRelacionamentos;
import com.agnaldo4j.bo.crm.Relacionamento;
import com.agnaldo4j.prm.ui.TelaCadastroDeContato;
import com.agnaldo4j.prm.ui.TelaCadastroDeConversa;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAO;
import com.agnaldo4j.prm.ui.manager.DialogInstance;
import com.agnaldo4j.prm.ui.manager.WindowManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JPopupMenu.Separator;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTree;

public class RelacionamentosTree extends JXTree implements Observer, TreeSelectionListener, ActionListener {
    
    private RelacionamentosTreeObservable relacionamentosTreeObservable;
    private RelacionamentosTreeModel model;
    private JPopupMenu popupGrupoDeContatos, popupDeContatos, popupDeConversa;
    
    private GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO;
    
    private GrupoDeRelacionamentos grupoDeRelacionamentosSelecionado;
    private Relacionamento relacionamentoSelecionado;
    
    private final WindowManager windowManager;
    private final TelaCadastroDeContato cadastroDeContato;
    private final TelaCadastroDeConversa cadastroDeConversa;
    
    public RelacionamentosTree(GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO, 
                               WindowManager windowManager,
                               RelacionamentosTreeModel model) {
        super(model);
        this.windowManager = windowManager;
        this.gerenciadorDeRelacionamentosDAO = gerenciadorDeRelacionamentosDAO;
        this.relacionamentosTreeObservable = new RelacionamentosTreeObservable();
        this.model = model;
        this.cadastroDeContato = (TelaCadastroDeContato)windowManager.getDialogInstance(DialogInstance.CadastroDeContato);
        this.cadastroDeConversa = (TelaCadastroDeConversa) windowManager.getDialogInstance(DialogInstance.CadastroDeConversa);
        this.gerenciadorDeRelacionamentosDAO.addObserver(this);
        this.addTreeSelectionListener(this);
        this.addMouseListener(new PopupListener());
        this.criaPopups();
        this.setSelectionRow(0);
        this.expandAll();
    }
    
    public void addObserver(Observer observer) {
        this.relacionamentosTreeObservable.addObserver(observer);
        Object object = ((DefaultMutableTreeNode)this.getModel().getRoot()).getUserObject();
        observer.update(this.relacionamentosTreeObservable, object);
    }
    
    public void update(Observable o, Object object) {
        this.atualizaTree((GerenciadorDeRelacionamentos)object);
        this.relacionamentosTreeObservable.notifyObservers(object);
        this.expandAll();
    }
    
    public void atualizaTree(GerenciadorDeRelacionamentos gerenciador) {
        this.model.limpaModel();
        this.model.setGruposDeRelacionamentos(gerenciador.getGruposDeRelacionamentos());
    }
    
    
    public void criaPopups() {
        this.criaPopupDeContatos();
        this.criaPopupGrupoDeContatos();
        this.criaPopupDeConversa();
    }
    private void criaPopupGrupoDeContatos() {
        JMenuItem menuItem = null;
        this.popupGrupoDeContatos = new JPopupMenu();
        menuItem = new JMenuItem("Adicionar grupo");
        menuItem.setActionCommand("adicionarGrupo");
        menuItem.addActionListener(this);
        popupGrupoDeContatos.add(menuItem);        
    }
    
    private void criaPopupDeContatos() {
        JMenuItem menuItem = null;
        this.popupDeContatos = new JPopupMenu();
        menuItem = new JMenuItem("Adicionar contato");
        menuItem.setActionCommand("adicionarContato");
        menuItem.addActionListener(this);
        popupDeContatos.add(menuItem);
        menuItem = new JMenuItem("Remover grupo de contatos");
        menuItem.setActionCommand("removerGrupoDeContatos");
        menuItem.addActionListener(this);
        popupDeContatos.add(menuItem);
        this.popupDeContatos.add(new Separator());
        menuItem = new JMenuItem("Pesquisar por conversas");
        menuItem.setActionCommand("pesquisarPorConversasNoGrupo");
        menuItem.addActionListener(this);
        this.popupDeContatos.add(menuItem);
    }
    
    private void criaPopupDeConversa() {
        JMenuItem menuItem = null;
        this.popupDeConversa = new JPopupMenu();
        menuItem = new JMenuItem("Adicionar conversa");
        menuItem.setActionCommand("adicionarConversa");
        menuItem.addActionListener(this);
        this.popupDeConversa.add(menuItem);
        menuItem = new JMenuItem("Remover contato");
        menuItem.setActionCommand("removerContato");
        menuItem.addActionListener(this);
        this.popupDeConversa.add(menuItem);
        this.popupDeConversa.add(new Separator());
        menuItem = new JMenuItem("Pesquisar por conversas");
        menuItem.setActionCommand("pesquisarPorConversasDoContato");
        menuItem.addActionListener(this);
        this.popupDeConversa.add(menuItem);
    }
    
    public void valueChanged(TreeSelectionEvent evento) {
        this.grupoDeRelacionamentosSelecionado = null;
        TreePath treePath = evento.getNewLeadSelectionPath();
        if (treePath!= null) {
            DefaultMutableTreeNode mutable = (DefaultMutableTreeNode) treePath.getLastPathComponent();
            Object object = mutable.getUserObject();
            this.relacionamentosTreeObservable.notifyObservers(object);
            if (object.getClass() == GrupoDeRelacionamentos.class) this.grupoDeRelacionamentosSelecionado = (GrupoDeRelacionamentos) object;
            else if (object.getClass() == Relacionamento.class) this.relacionamentoSelecionado = (Relacionamento) object;
        }
    }
    
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals("adicionarGrupo")) this.adicionarGrupoDeRelacionamentosClicked();
        else if (evento.getActionCommand().equals("adicionarContato")) this.adicionarRelacionamentoClicked();
        else if (evento.getActionCommand().equals("adicionarConversa")) this.adicionarConversaClicked();
        else if (evento.getActionCommand().equals("removerContato")) this.removerContatoClicked();
        else if (evento.getActionCommand().equals("removerGrupoDeContatos")) this.removerGrupoDeContatosClicked();
        else if (evento.getActionCommand().equals("pesquisarPorConversasNoGrupo")) this.pesquisarPorConversasNoGrupoClicked();
        else if (evento.getActionCommand().equals("pesquisarPorConversasDoContato")) this.pesquisarPorConversasDoContatoClicked();
    }
    
    public void removerGrupoDeContatosClicked() {
        GerenciadorDeRelacionamentos gerenciador = this.gerenciadorDeRelacionamentosDAO.getGerenciadorDeRelacionamentos();
        gerenciador.removeGrupoDeRelacionamentos(this.grupoDeRelacionamentosSelecionado);
        
        this.gerenciadorDeRelacionamentosDAO.gravar();
    }
    
    public void removerContatoClicked() {
        GerenciadorDeRelacionamentos gerenciador = this.gerenciadorDeRelacionamentosDAO.getGerenciadorDeRelacionamentos();
        Set<GrupoDeRelacionamentos> listaDeGrupoDeRelacionamentos = gerenciador.getGruposDeRelacionamentos();
        
        
        
        for (GrupoDeRelacionamentos grupoDeRelacionamentos : listaDeGrupoDeRelacionamentos) {
            if (grupoDeRelacionamentos.getNome() != null && grupoDeRelacionamentos.getNome().equals(this.relacionamentoSelecionado.getTipoContato())) {
                grupoDeRelacionamentos.removeRelacionamento(this.relacionamentoSelecionado);
            }
        }
        
        this.gerenciadorDeRelacionamentosDAO.gravar();
    }
    
    public void adicionarConversaClicked() {
        if (this.relacionamentoSelecionado != null) {
            this.cadastroDeConversa.setRelacionamentoSelecionado(this.relacionamentoSelecionado);
            this.cadastroDeConversa.setVisible(true);
        }
    }
    
    public void adicionarRelacionamentoClicked() {
        if (this.grupoDeRelacionamentosSelecionado != null) {
            this.cadastroDeContato.setGrupoDeRelacionamentosSelecionado(this.grupoDeRelacionamentosSelecionado);
            this.cadastroDeContato.setVisible(true);
        }
    }
    
    public void adicionarGrupoDeRelacionamentosClicked() {
        String nomeGrupo = JOptionPane.showInputDialog(null,"Nome do novo grupo", "", JOptionPane.QUESTION_MESSAGE);
        if (nomeGrupo != null && !nomeGrupo.trim().equals("")) {
            GrupoDeRelacionamentos grupo = new GrupoDeRelacionamentos(nomeGrupo, new HashSet<Relacionamento>());
            this.gerenciadorDeRelacionamentosDAO.getGerenciadorDeRelacionamentos().addGrupoDeRelacionamentos(grupo);
            this.gerenciadorDeRelacionamentosDAO.gravar();
        }
    }

    private void pesquisarPorConversasNoGrupoClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void pesquisarPorConversasDoContatoClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    class RelacionamentosTreeObservable extends Observable {
        public void notifyObservers(Object object) {
            this.setChanged();
            super.notifyObservers(object);
        }
    }
    
    class PopupListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            maybeShowPopup(e);
        }
        
        public void mouseReleased(MouseEvent e) {
            maybeShowPopup(e);
        }
        
        private void maybeShowPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                if (getSelectionPath() != null) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode)getSelectionPath().getLastPathComponent();
                    Object object = node.getUserObject();
                    if (object.getClass() == GerenciadorDeRelacionamentos.class) {
                        popupGrupoDeContatos.show(e.getComponent(), e.getX(), e.getY());
                    } else if (object.getClass() == GrupoDeRelacionamentos.class) {
                        popupDeContatos.show(e.getComponent(), e.getX(), e.getY());
                    } else if (object.getClass() == Relacionamento.class) {
                        popupDeConversa.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        }
    }
}