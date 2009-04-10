package com.agnaldo4j.prm.ui.tree;

import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import com.agnaldo4j.bo.crm.GrupoDeRelacionamentos;
import com.agnaldo4j.bo.crm.Relacionamento;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class RelacionamentosTreeCellRenderer extends DefaultTreeCellRenderer {
    
    public RelacionamentosTreeCellRenderer() {}
    
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
        Object object = node.getUserObject();
        
        if (object.getClass() == GerenciadorDeRelacionamentos.class)  this.setaIconesGerenciadorDeRelacionamentos();
        else if (object.getClass() == GrupoDeRelacionamentos.class) this.setaIconesGrupoDeRelacionamentos();
        else if (object.getClass() == Relacionamento.class) this.setaIconesRelacionamento();

        return this;
    }
    
    public void setaIconesGerenciadorDeRelacionamentos() {
        Icon globo = new ImageIcon(getClass().getResource("/globo.png"));
        this.setIcon(globo);
    }
    
    public void setaIconesGrupoDeRelacionamentos() {
        Icon grupo = new ImageIcon(getClass().getResource("/grupo.png"));
        this.setIcon(grupo);
    }
    
    public void setaIconesRelacionamento() {
        Icon relacionamento = new ImageIcon(getClass().getResource("/relacionamento.png"));
        this.setIcon(relacionamento);
    }
}