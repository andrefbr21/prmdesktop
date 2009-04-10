package com.agnaldo4j.prm.ui.calendar;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.Relacionamento;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ConversasDoDiaView extends JDialog implements ListSelectionListener {
    
    private Vector<Conversa> listVector = new Vector<Conversa>();
    public ConversasDoDiaView() {
        super();        
        this.setModal(true);
        initComponents();
        list.addListSelectionListener(this);
        this.list.setValueIsAdjusting(true);
        this.list.setListData(listVector);
    }
    
    public void setConversas(Map<Relacionamento, List<Conversa>> conversasMap) {
        pack();
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-this.getWidth() - 1)/2, (screenSize.height-this.getHeight() -1)/2, this.getWidth() -1, this.getHeight() -1);
        
        Set<Relacionamento> relacionamentosKey = conversasMap.keySet();
        listVector.clear();
        for (Relacionamento relacionamento : relacionamentosKey) {
            List<Conversa> converasValue = conversasMap.get(relacionamento);            
            listVector.addAll(converasValue);            
        }        
    }
    
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        conteudoConversaTextArea.setText("");
        list.clearSelection();
        list.setVisibleRowCount(listVector.size());
        Dimension dimension = getSize();
        setSize((int)dimension.getWidth() + 1, (int)dimension.getHeight() + 1);
    }
    
    public void valueChanged(ListSelectionEvent event) {
        Conversa conversa = (Conversa)this.list.getSelectedValue();
        if (conversa != null) this.conteudoConversaTextArea.setText(conversa.getConteudo());
    }
    
    private static final class MessageList extends JList {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int height = getHeight();
            int prefHeight = getPreferredSize().height;
            if (prefHeight < height) {
                int modelSize = getModel().getSize();
                int cellHeight = getFixedCellHeight();
                int startRow = modelSize / 2 * 2;
                if (startRow < modelSize) {
                    startRow += 2;
                }
                int y = startRow * cellHeight;
                int w = getWidth();
                g.setColor(MessageListCellRenderer.STRIPE_COLOR);
                while (y < height) {
                    g.fillRect(0, y, w, cellHeight);
                    y += cellHeight + cellHeight;
                }
            }
        }
    }
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane2 = new javax.swing.JScrollPane();
        conteudoConversaTextArea = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new MessageList();
        list.setCellRenderer(new MessageListCellRenderer());
        list.setPrototypeCellValue("xxx");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        conteudoConversaTextArea.setColumns(20);
        conteudoConversaTextArea.setRows(10);
        conteudoConversaTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        conteudoConversaTextArea.setEnabled(false);
        jScrollPane2.setViewportView(conteudoConversaTextArea);

        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        list.setVisibleRowCount(0);
        jScrollPane1.setViewportView(list);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea conteudoConversaTextArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList list;
    // End of variables declaration//GEN-END:variables
}