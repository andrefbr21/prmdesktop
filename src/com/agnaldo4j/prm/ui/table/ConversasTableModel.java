package com.agnaldo4j.prm.ui.table;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.Relacionamento;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;

public class ConversasTableModel extends DefaultTableModel {
    
    private SimpleDateFormat simpleDateFormat;
    public ConversasTableModel() {
        super(new Object[]{"Titulo", "Relacionamento", "Data"}, 0);
        this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    
    public void limparTabela() {
        int quantidadeDeLinhas = this.getRowCount() - 1;
        for (int i = quantidadeDeLinhas; i > -1; i--) this.removeRow(i);
    }
    
    public Conversa getConversa(int linha) {
        return (Conversa)this.getValueAt(linha, 0);
    }
    
    public void atualizaTabela(Map<Relacionamento, List<Conversa>> conversasMap) {
        this.limparTabela();
        Set<Relacionamento> relacionamentos = conversasMap.keySet();
        for (Relacionamento relacionamento : relacionamentos) {
            List<Conversa> conversas = conversasMap.get(relacionamento);
            for (Conversa conversa : conversas) {
                this.addRow(new Object[]{conversa, relacionamento, this.simpleDateFormat.format(conversa.getData())});
            }
        }
    }
}
