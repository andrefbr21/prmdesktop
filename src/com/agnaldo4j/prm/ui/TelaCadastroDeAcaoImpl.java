package com.agnaldo4j.prm.ui;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCadastroDeAcaoImpl extends javax.swing.JDialog implements ActionListener, TelaCadastroDeAcao {
    
    private Conversa conversaSelecionada;
    private GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO;
    
    public TelaCadastroDeAcaoImpl(GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO) {
        super();
        this.gerenciadorDeRelacionamentosDAO = gerenciadorDeRelacionamentosDAO;
        this.setModal(true);
        initComponents();
        
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2, this.getWidth(), this.getHeight());
    }
        
    public void setConversa(Conversa conversaSelecionada) {
        this.conversaSelecionada = conversaSelecionada;
    }
    
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals(this.confirmarBt.getActionCommand())) this.confirmarBtClicked();
        else if (evento.getActionCommand().equals(this.cancelarBt.getActionCommand())) this.cancelarBtClicked();
    }
    
    public void confirmarBtClicked() {
        if (this.conversaSelecionada != null) {
            
        }
        
        this.conversaSelecionada = null;
        this.setVisible(false);
    }
    
    public void cancelarBtClicked() {
        this.conversaSelecionada = null;
        this.setVisible(false);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        tituloTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        dataInicioDatePicker = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descricaoTextArea = new javax.swing.JTextArea();
        confirmarBt = new javax.swing.JButton();
        cancelarBt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jLabel1.setText("Titulo:");

        jLabel2.setText("Data inicio:");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Descricao"));
        descricaoTextArea.setColumns(20);
        descricaoTextArea.setRows(5);
        jScrollPane1.setViewportView(descricaoTextArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
        );

        confirmarBt.setText("confirmar");

        cancelarBt.setText("cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataInicioDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(114, 114, 114))
                            .addComponent(tituloTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(confirmarBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelarBt)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tituloTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dataInicioDatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarBt)
                    .addComponent(confirmarBt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarBt;
    private javax.swing.JButton confirmarBt;
    private org.jdesktop.swingx.JXDatePicker dataInicioDatePicker;
    private javax.swing.JTextArea descricaoTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField tituloTextField;
    // End of variables declaration//GEN-END:variables
    
}
