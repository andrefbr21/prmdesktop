package com.agnaldo4j.prm.ui;

import com.agnaldo4j.prm.clock.CalendarWrapperImpl;
import com.agnaldo4j.prm.ui.dao.GerenciadorDeRelacionamentosDAO;
import com.agnaldo4j.prm.ui.calendar.DayViewPanel;
import com.agnaldo4j.prm.ui.manager.WindowManager;
import com.agnaldo4j.prm.ui.tree.RelacionamentosTree;
import com.agnaldo4j.prm.ui.tree.RelacionamentosTreeCellRenderer;
import com.agnaldo4j.prm.ui.tree.RelacionamentosTreeModel;
import java.awt.BorderLayout;
import java.util.GregorianCalendar;
import javax.swing.JFrame;

public class PRMDesktop extends JFrame {
    
    private final GerenciadorDeRelacionamentosDAO gerenciadorDeRelacionamentosDAO;
    private final WindowManager windowManager;
    private RelacionamentosTreeModel relacionamentosTreeModel;
        
    public PRMDesktop(GerenciadorDeRelacionamentosDAO GerenciadorDeRelacionamentosDAO, WindowManager windowManager) {
        this.gerenciadorDeRelacionamentosDAO = GerenciadorDeRelacionamentosDAO;
        this.windowManager = windowManager;
        this.relacionamentosTreeModel = new RelacionamentosTreeModel(this.gerenciadorDeRelacionamentosDAO.getGerenciadorDeRelacionamentos());
        
        initComponents();
        
        DayViewPanel dayViewPanel = new DayViewPanel(new CalendarWrapperImpl(GregorianCalendar.getInstance()));
        
        ((RelacionamentosTree)this.relacionamentosTree).addObserver(dayViewPanel);
        
        this.calendarPanel.add(dayViewPanel, BorderLayout.CENTER);
        
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-this.getWidth())/2, (screenSize.height-this.getHeight())/2, this.getWidth(), this.getHeight());
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        mainPanel = new javax.swing.JPanel();
        arvoreDeContatosSP = new javax.swing.JScrollPane();

        relacionamentosTree = new RelacionamentosTree(this.gerenciadorDeRelacionamentosDAO, this.windowManager, this.relacionamentosTreeModel);
        calendarScrollPanel = new javax.swing.JScrollPane();
        calendarPanel = new javax.swing.JPanel();
        toolBar = new javax.swing.JToolBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PRM 1.0");
        setBackground(new java.awt.Color(201, 215, 170));
        setForeground(new java.awt.Color(201, 215, 170));
        setName("prmDesktop");
        relacionamentosTree.setCellRenderer(new RelacionamentosTreeCellRenderer());
        arvoreDeContatosSP.setViewportView(relacionamentosTree);

        calendarPanel.setLayout(new java.awt.BorderLayout());

        calendarScrollPanel.setViewportView(calendarPanel);

        toolBar.setFloatable(false);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addComponent(arvoreDeContatosSP, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calendarScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE))
                    .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(arvoreDeContatosSP, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
                    .addComponent(calendarScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)))
        );
        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane arvoreDeContatosSP;
    private javax.swing.JPanel calendarPanel;
    private javax.swing.JScrollPane calendarScrollPanel;
    private javax.swing.JPanel mainPanel;
    private org.jdesktop.swingx.JXTree relacionamentosTree;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
    
}