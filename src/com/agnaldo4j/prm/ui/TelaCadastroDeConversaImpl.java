package com.agnaldo4j.prm.ui;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.Relacionamento;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAO;
import com.agnaldo4j.prm.ui.manager.DialogInstance;
import com.agnaldo4j.prm.ui.manager.WindowManager;
import com.agnaldo4j.prm.ui.table.AcoesTable;
import com.agnaldo4j.prm.ui.table.AcoesTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JDialog;

public class TelaCadastroDeConversaImpl extends JDialog implements TelaCadastroDeConversa, ActionListener {
    
    private Relacionamento relacionamentoSelecionado;
    private Conversa conversa;
    private final GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO;
    private final WindowManager windowManager;
    
    public TelaCadastroDeConversaImpl(GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO, WindowManager windowManager) {
        super();
        this.gerenciadorDeRelacionamentosDAO = gerenciadorDeRelacionamentosDAO;
        this.windowManager = windowManager;
        this.setModal(true);
        initComponents();
                
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2, this.getWidth(), this.getHeight());
    }
    
    public void setRelacionamentoSelecionado(Relacionamento relacionamentoSelecionado) {
        this.relacionamentoSelecionado = relacionamentoSelecionado;
    }
    
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals(this.confirmarBt.getActionCommand())) this.confirmarBtClicked();
        else if (evento.getActionCommand().equals(this.cancelarBt.getActionCommand())) this.cancelarBtClicked();
        else if (evento.getActionCommand().equals(this.adicionarAcaoBt.getActionCommand())) this.adicionarAcaoBtClicked();
        else if (evento.getActionCommand().equals(this.editarAcaoBt.getActionCommand())) this.editarAcaoBtClicked();
        else if (evento.getActionCommand().equals(this.removerAcaoBt.getActionCommand())) this.removerAcaoBtClicked();
    }
    
    private void confirmarBtClicked() {
        if (this.conversa == null) this.conversa = new Conversa();
        this.conversa.setTitulo(this.tituloConversaTextField.getText());
        
        this.conversa.setData(new Date());
        this.conversa.setConteudo(this.conversaTextArea.getText());
        this.conversa.setPessoa(this.relacionamentoSelecionado.getPessoa());
        this.relacionamentoSelecionado.addConversa(this.conversa);
        this.gerenciadorDeRelacionamentosDAO.gravar();
        this.conversa = null;
        this.setVisible(false);
    }

    private void cancelarBtClicked() {
        this.setVisible(false);
        this.relacionamentoSelecionado = null;
    }

    private void adicionarAcaoBtClicked() {
        if (this.conversa == null) this.conversa = new Conversa();
        TelaCadastroDeAcaoImpl telaCadastroDeAcao = (TelaCadastroDeAcaoImpl)this.windowManager.getDialogInstance(DialogInstance.CadastroDeAcao);
        telaCadastroDeAcao.setVisible(true);
    }

    private void editarAcaoBtClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private void removerAcaoBtClicked() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        tituloConversaTextField = new javax.swing.JTextField();
        cancelarBt = new javax.swing.JButton();
        this.cancelarBt.addActionListener(this);
        confirmarBt = new javax.swing.JButton();
        this.confirmarBt.addActionListener(this);
        conversaTabbedPane = new javax.swing.JTabbedPane();
        conversaPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        conversaTextArea = new javax.swing.JTextArea();
        acoesPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        acoesTable = new AcoesTable();
        adicionarAcaoBt = new javax.swing.JButton();
        this.adicionarAcaoBt.addActionListener(this);
        editarAcaoBt = new javax.swing.JButton();
        this.editarAcaoBt.addActionListener(this);
        removerAcaoBt = new javax.swing.JButton();
        this.removerAcaoBt.addActionListener(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setText("Titulo:");

        cancelarBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cancelar.png")));
        cancelarBt.setActionCommand("cancelarBt");

        confirmarBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/confirmar.png")));
        confirmarBt.setActionCommand("confirmarBt");

        conversaTextArea.setColumns(20);
        conversaTextArea.setRows(5);
        jScrollPane1.setViewportView(conversaTextArea);

        javax.swing.GroupLayout conversaPanelLayout = new javax.swing.GroupLayout(conversaPanel);
        conversaPanel.setLayout(conversaPanelLayout);
        conversaPanelLayout.setHorizontalGroup(
            conversaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conversaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                .addContainerGap())
        );
        conversaPanelLayout.setVerticalGroup(
            conversaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(conversaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addContainerGap())
        );
        conversaTabbedPane.addTab("Conversa", conversaPanel);

        acoesTable.setModel(new AcoesTableModel());
        jScrollPane2.setViewportView(acoesTable);

        adicionarAcaoBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png")));
        adicionarAcaoBt.setActionCommand("adicionarAcaoBt");
        adicionarAcaoBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        editarAcaoBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/edit.png")));
        editarAcaoBt.setActionCommand("editarAcaoBt");
        editarAcaoBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        removerAcaoBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete.png")));
        removerAcaoBt.setActionCommand("removerAcaoBt");
        removerAcaoBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        javax.swing.GroupLayout acoesPanelLayout = new javax.swing.GroupLayout(acoesPanel);
        acoesPanel.setLayout(acoesPanelLayout);
        acoesPanelLayout.setHorizontalGroup(
            acoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acoesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(acoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(editarAcaoBt)
                        .addComponent(adicionarAcaoBt))
                    .addComponent(removerAcaoBt))
                .addContainerGap())
        );
        acoesPanelLayout.setVerticalGroup(
            acoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acoesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(acoesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addGroup(acoesPanelLayout.createSequentialGroup()
                        .addComponent(adicionarAcaoBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editarAcaoBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removerAcaoBt)))
                .addContainerGap())
        );
        conversaTabbedPane.addTab("Acoes", acoesPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(conversaTabbedPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmarBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarBt))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tituloConversaTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tituloConversaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(conversaTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarBt)
                    .addComponent(cancelarBt))
                .addContainerGap())
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acoesPanel;
    private org.jdesktop.swingx.JXTable acoesTable;
    private javax.swing.JButton adicionarAcaoBt;
    private javax.swing.JButton cancelarBt;
    private javax.swing.JButton confirmarBt;
    private javax.swing.JPanel conversaPanel;
    private javax.swing.JTabbedPane conversaTabbedPane;
    private javax.swing.JTextArea conversaTextArea;
    private javax.swing.JButton editarAcaoBt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton removerAcaoBt;
    private javax.swing.JTextField tituloConversaTextField;
    // End of variables declaration//GEN-END:variables
}