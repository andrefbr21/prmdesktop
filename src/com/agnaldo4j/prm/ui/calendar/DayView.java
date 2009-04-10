package com.agnaldo4j.prm.ui.calendar;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.Relacionamento;
import com.agnaldo4j.prm.ui.calendar.query.ConsultaConversas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import javax.swing.JLabel;

public class DayView extends JLabel implements MouseListener {
    private Color corPadrao;
    private Color corAntiga;
    private Calendar calendar;
    private Object objetoSelecionado;
    private Map<Relacionamento, List<Conversa>> conversasDoDia;
    private Map<Class, ConsultaConversas> consultas;
    private ConversasDoDiaView conversasDoDiaView;
    
    public DayView(Map<Class, ConsultaConversas> consultas, ConversasDoDiaView conversasDoDiaView) {
        this.conversasDoDiaView = conversasDoDiaView;
        this.consultas = consultas;
        this.setCalendar(null, null);
        this.configuracaoPadrao();
        this.corPadrao = this.getBackground();
        this.corAntiga = this.corPadrao;
    }

    public void setCalendar(Calendar calendar, Object objetoSelecionado) {
        this.setText("");
        this.setEnabled(false);
        this.calendar = calendar;
        this.objetoSelecionado = objetoSelecionado;
        if (this.calendar != null) {
            this.setText(String.valueOf(this.calendar.get(Calendar.DAY_OF_MONTH)));
            this.setEnabled(true);
            this.buscaConversasParaEsteDia();
        }
    }
    
    public void buscaConversasParaEsteDia() {
        if (this.calendar != null && this.objetoSelecionado != null) {
            if (this.conversasDoDia != null) this.conversasDoDia.clear();
            this.conversasDoDia = this.consultas.get(this.objetoSelecionado.getClass()).recuperaConversasDoDia(this.calendar, this.objetoSelecionado);
            this.formataParaFacilitarAIdentificacaoDeExistenciaDeConversa();
        }
    }
    
    public void formataParaFacilitarAIdentificacaoDeExistenciaDeConversa() {
        if (this.conversasDoDia.size() > 0) formataComConversa();
        else formataSemConversa();
    }
    
    public void formataComConversa() {
        this.corAntiga = Color.decode("#8EA9C4");
        this.setBackground(this.corAntiga);
    }
    
    public void formataSemConversa() {
        this.corAntiga = this.corPadrao;
        this.setBackground(this.corAntiga);
    }
    
    public void configuracaoPadrao() {
        this.setVerticalAlignment(JLabel.TOP);
        this.setHorizontalAlignment(JLabel.LEFT);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {
        if (this.conversasDoDia != null && this.conversasDoDia.size() > 0) { 
            this.conversasDoDiaView.setConversas(this.conversasDoDia);
            this.conversasDoDiaView.setVisible(true);
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (this.isEnabled()) this.setBackground(Color.LIGHT_GRAY);
    }

    public void mouseExited(MouseEvent e) {
        if (this.isEnabled()) this.setBackground(corAntiga);
    }

    public void update(Observable observable, Object objetoSelecionado) {
        if (this.isEnabled()) {
            this.objetoSelecionado = objetoSelecionado;
            this.buscaConversasParaEsteDia();
        }
    }
}