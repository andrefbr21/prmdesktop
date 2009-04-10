package com.agnaldo4j.prm.ui;

import com.agnaldo4j.bo.crm.GrupoDeRelacionamentos;
import com.agnaldo4j.bo.crm.Relacionamento;
import com.agnaldo4j.bo.pessoa.PessoaFisica;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAO;
import com.agnaldo4j.prm.ui.filechooser.ImageFileView;
import com.agnaldo4j.prm.ui.filechooser.ImageFilter;
import com.agnaldo4j.prm.ui.filechooser.ImagePreview;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

public class TelaCadastroDeContatoImpl extends JDialog implements TelaCadastroDeContato, ActionListener {
   
    private final GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO;
    private GrupoDeRelacionamentos grupoDeRelacionamentosSelecionado;
    private JFileChooser fileChooser;
    
    public TelaCadastroDeContatoImpl(GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO) {
        super();
        this.setModal(true);
        this.fileChooser = new JFileChooser();
        this.fileChooser.setFileHidingEnabled(false);
        this.fileChooser.setAcceptAllFileFilterUsed(false);
        this.fileChooser.setFileFilter(new ImageFilter());
        this.fileChooser.setFileView(new ImageFileView());
        this.fileChooser.setAccessory(new ImagePreview(this.fileChooser));
        
        this.gerenciadorDeRelacionamentosDAO = gerenciadorDeRelacionamentosDAO;
        initComponents();
        
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2, this.getWidth(), this.getHeight());
    }

    public void gravarContatoClicked() {
        if (this.grupoDeRelacionamentosSelecionado != null) {
            try {
                
                PessoaFisica pessoa = new PessoaFisica();
                pessoa.setNome(this.pessoaNomeTf.getText());
                pessoa.setFantasia(this.pessoaApelidoTf.getText());
                if( pessoa.getDataCadastro() == null) pessoa.setDataCadastro(new Date());
                this.pessoaDataNascimentoDp.commitEdit();
                pessoa.setDataNascimento(this.pessoaDataNascimentoDp.getDate());
                this.pessoaAlturaFtf.commitEdit();
                if (this.pessoaAlturaFtf.getText() != null && !this.pessoaAlturaFtf.getText().trim().equals("")) pessoa.setAltura(Float.valueOf(this.pessoaAlturaFtf.getText()));
                this.pessoaPesoFtf.commitEdit();
                if (this.pessoaPesoFtf.getText() != null && !this.pessoaPesoFtf.getText().trim().equals("")) pessoa.setPeso(Float.valueOf(this.pessoaPesoFtf.getText()));
                pessoa.setSexo((String)this.pessoaSexoCb.getSelectedItem());
                pessoa.setEstadoCivil((String)this.pessoaEstadoCivilCb.getSelectedItem());
                pessoa.setFoto(this.contatoFotoTextField.getText());
                
                Relacionamento relacionamento = new Relacionamento();
                relacionamento.setDataCadastro(new Date());
                this.pessoaDataConhecimentoDp.commitEdit();
                relacionamento.setDataConhecimento(this.pessoaDataConhecimentoDp.getDate());
                relacionamento.setPessoa(pessoa);
                relacionamento.setTipoContato(this.grupoDeRelacionamentosSelecionado.getNome());
                
                this.grupoDeRelacionamentosSelecionado.addRelacionamento(relacionamento);
                
                this.gerenciadorDeRelacionamentosDAO.gravar();
                
                this.setVisible(false);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void cancelarContatoClicked() {
        this.setVisible(false);
    }
    
    public void setGrupoDeRelacionamentosSelecionado(GrupoDeRelacionamentos grupoDeRelacionamentosSelecionado) {
        this.grupoDeRelacionamentosSelecionado = grupoDeRelacionamentosSelecionado;
    }
    
    public void actionPerformed(ActionEvent event) {
        if (event.getActionCommand().equalsIgnoreCase("gravar")) this.gravarContatoClicked();
        else if (event.getActionCommand().equalsIgnoreCase("cancelar")) this.cancelarContatoClicked();
        else if (event.getActionCommand().equalsIgnoreCase(this.contatoFotoBt.getActionCommand())) this.contatoFotoBtClicked();
    }
    
    private void contatoFotoBtClicked() {
        int retorno = this.fileChooser.showDialog(this, "Attach");
        if (retorno == JFileChooser.APPROVE_OPTION) {
            this.contatoFotoTextField.setText(this.fileChooser.getSelectedFile().getAbsolutePath());
        }
        this.fileChooser.setSelectedFile(null);
        
    }
  
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        botoesPanel = new javax.swing.JPanel();
        pessoaGravarBt = new javax.swing.JButton();
        pessoaGravarBt.setActionCommand("gravar");
        pessoaGravarBt.addActionListener(this);
        pessoaCancelarBt = new javax.swing.JButton();
        pessoaCancelarBt.setActionCommand("cancelar");
        pessoaCancelarBt.addActionListener(this);
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pessoaNomeTf = new javax.swing.JTextField();
        pessoaApelidoTf = new javax.swing.JTextField();
        pessoaPesoFtf = new javax.swing.JFormattedTextField();
        pessoaEstadoCivilCb = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pessoaAlturaFtf = new javax.swing.JFormattedTextField();
        pessoaDataNascimentoDp = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        pessoaSexoCb = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        pessoaDataConhecimentoDp = new org.jdesktop.swingx.JXDatePicker();
        jLabel8 = new javax.swing.JLabel();
        contatoFotoTextField = new javax.swing.JTextField();
        contatoFotoBt = new javax.swing.JButton();
        this.contatoFotoBt.addActionListener(this);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de contato");
        botoesPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        pessoaGravarBt.setText("gravar");
        botoesPanel.add(pessoaGravarBt);

        pessoaCancelarBt.setText("cancelar");
        botoesPanel.add(pessoaCancelarBt);

        jLabel6.setText("Peso:");

        jLabel2.setText("Apelido:");

        jLabel1.setText("Nome:");

        jLabel4.setText("Estado Civil:");

        pessoaEstadoCivilCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Casado(a)", "Solteiro(a)", "Viuvo(a)", "Divorciado(a)" }));

        jLabel5.setText("Data Nasc.:");

        jLabel7.setText("Altura:");

        jLabel3.setText("Sexo:");

        pessoaSexoCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Selecione", "Masculino", "Feminino" }));

        jLabel17.setText("Data Conhecimento:");

        jLabel8.setText("Foto:");

        contatoFotoTextField.setEditable(false);

        contatoFotoBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/folder_view.png")));
        contatoFotoBt.setActionCommand("contatoFotoBt");
        contatoFotoBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(59, 59, 59)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel3)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1)))
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pessoaNomeTf, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(pessoaApelidoTf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pessoaPesoFtf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pessoaSexoCb, javax.swing.GroupLayout.Alignment.LEADING, 0, 165, Short.MAX_VALUE)
                            .addComponent(pessoaDataConhecimentoDp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pessoaDataNascimentoDp, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(pessoaEstadoCivilCb, 0, 154, Short.MAX_VALUE)
                            .addComponent(pessoaAlturaFtf, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(contatoFotoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contatoFotoBt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pessoaNomeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pessoaApelidoTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel5))
                    .addComponent(pessoaDataNascimentoDp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pessoaSexoCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(pessoaEstadoCivilCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pessoaPesoFtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(pessoaAlturaFtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pessoaDataConhecimentoDp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(contatoFotoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contatoFotoBt)
                        .addGap(20, 20, 20))))
        );
        jTabbedPane1.addTab("Dados Essenciais", jPanel5);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(botoesPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botoesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel botoesPanel;
    private javax.swing.JButton contatoFotoBt;
    private javax.swing.JTextField contatoFotoTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JFormattedTextField pessoaAlturaFtf;
    private javax.swing.JTextField pessoaApelidoTf;
    private javax.swing.JButton pessoaCancelarBt;
    private org.jdesktop.swingx.JXDatePicker pessoaDataConhecimentoDp;
    private org.jdesktop.swingx.JXDatePicker pessoaDataNascimentoDp;
    private javax.swing.JComboBox pessoaEstadoCivilCb;
    private javax.swing.JButton pessoaGravarBt;
    private javax.swing.JTextField pessoaNomeTf;
    private javax.swing.JFormattedTextField pessoaPesoFtf;
    private javax.swing.JComboBox pessoaSexoCb;
    // End of variables declaration//GEN-END:variables
    
}
