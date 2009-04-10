package com.agnaldo4j.prm.ui.tree;

import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import com.agnaldo4j.bo.crm.GrupoDeRelacionamentos;
import com.agnaldo4j.bo.crm.Relacionamento;
import java.util.Iterator;
import java.util.Set;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class RelacionamentosTreeModel extends DefaultTreeModel {
    
    private DefaultMutableTreeNode root;
    
    public RelacionamentosTreeModel(GerenciadorDeRelacionamentos gerenciador) {
        super(new DefaultMutableTreeNode(gerenciador));
        this.root = (DefaultMutableTreeNode)this.getRoot();
        this.setGruposDeRelacionamentos(gerenciador.getGruposDeRelacionamentos());
    }
    
    public void limpaModel() {
        this.root.removeAllChildren();
        this.nodeStructureChanged((TreeNode)this.root);
    }
    
    public void setGruposDeRelacionamentos(Set<GrupoDeRelacionamentos> grupos) {
        if (grupos != null) {
            for (GrupoDeRelacionamentos grupoDeRelacionamentos : grupos) {
                DefaultMutableTreeNode novoGrupo = new DefaultMutableTreeNode(grupoDeRelacionamentos);
                Set<Relacionamento> relacionamentos = grupoDeRelacionamentos.getRelacionamentos();
                for (Relacionamento relacionamento : relacionamentos) {
                    novoGrupo.add(new DefaultMutableTreeNode(relacionamento));
                }            
                this.root.add(novoGrupo);
                this.nodeStructureChanged((TreeNode)this.root);
            }
        }
    }
}