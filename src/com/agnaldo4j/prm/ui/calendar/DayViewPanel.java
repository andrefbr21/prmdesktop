package com.agnaldo4j.prm.ui.calendar;

import com.agnaldo4j.bo.crm.GerenciadorDeRelacionamentos;
import com.agnaldo4j.bo.crm.GrupoDeRelacionamentos;
import com.agnaldo4j.bo.crm.Relacionamento;
import com.agnaldo4j.prm.clock.CalendarWrapper;
import com.agnaldo4j.prm.ui.calendar.query.ConsultaConversas;
import com.agnaldo4j.prm.ui.calendar.query.ConsultaConversasDoGerenciadorDeRelacionamentos;
import com.agnaldo4j.prm.ui.calendar.query.ConsultaConversasDoGrupoDeRelacionamentos;
import com.agnaldo4j.prm.ui.calendar.query.ConsultaConversasDoRelacionamento;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class DayViewPanel extends JPanel implements ActionListener, Observer {
    
    private Object objetoSelecionado;
    private CalendarWrapper calendar;
    private String[] meses = new String[]{"Janeiro", "Fevereiro", "Marco", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private JLabel[][] mes = new JLabel[6][7];
    private Map<Class, ConsultaConversas> consultasParaConversaDoDia;
    private ConversasDoDiaView conversasDoDiaView;
    
    public DayViewPanel(CalendarWrapper calendar) {
        this.calendar = calendar;
        this.conversasDoDiaView = new ConversasDoDiaView();
        this.consultasParaConversaDoDia = new HashMap<Class, ConsultaConversas>();
        this.consultasParaConversaDoDia.put(GerenciadorDeRelacionamentos.class, new ConsultaConversasDoGerenciadorDeRelacionamentos());
        this.consultasParaConversaDoDia.put(GrupoDeRelacionamentos.class, new ConsultaConversasDoGrupoDeRelacionamentos());
        this.consultasParaConversaDoDia.put(Relacionamento.class, new ConsultaConversasDoRelacionamento());
        initComponents();
        this.organizaLabelViews();
        this.mostraMes(this.calendar.diasDoMes());
    }
    
    public void mostraMes(List<Calendar> diasDoMes) {
        this.limpaTudo();
        for (Calendar calendar : diasDoMes) {
            int semana = calendar.get(Calendar.WEEK_OF_MONTH);
            int diaDaSemana = calendar.get(Calendar.DAY_OF_WEEK);
        
            DayView dayView = (DayView) mes[semana - 1][diaDaSemana - 1];
            dayView.setCalendar(calendar, this.objetoSelecionado);
            
            this.mesLabel.setText(meses[calendar.get(Calendar.MONTH)]);
            this.anoLabel.setText(String.valueOf(calendar.get(Calendar.YEAR)));
        }
    }
    
    public void limpaTudo() {
        for (JLabel[] labels : mes) {
            for(JLabel label : labels)
                ((DayView)label).setCalendar(null, this.objetoSelecionado);
        }
        this.mesLabel.setText("");
        this.anoLabel.setText("");
    }
    
    public void organizaLabelViews() {
        this.montaPrimeiraSemana();
        this.montaSegundaSemana();
        this.montaTerceiraSemana();
        this.montaQuartaSemana();
        this.montaQuintaSemana();
        this.montaSextaSemana();
    }
    
    public void montaPrimeiraSemana() {
        this.mes[0][0] = this.domingoUmLabel;
        this.mes[0][1] = this.segundaUmLabel;
        this.mes[0][2] = this.tercaUmLabel;
        this.mes[0][3] = this.quartaUmLabel;
        this.mes[0][4] = this.quintaUmLabel;
        this.mes[0][5] = this.sextaUmLabel;
        this.mes[0][6] = this.sabadoUmLabel;
    }
    
    public void montaSegundaSemana() {
        this.mes[1][0] = this.domingoDoisLabel;
        this.mes[1][1] = this.segundaDoisLabel;
        this.mes[1][2] = this.tercaDoisLabel;
        this.mes[1][3] = this.quartaDoisLabel;
        this.mes[1][4] = this.quintaDoisLabel;
        this.mes[1][5] = this.sextaDoisLabel;
        this.mes[1][6] = this.sabadoDoisLabel;
    }
    
    public void montaTerceiraSemana() {
        this.mes[2][0] = this.domingoTresLabel;
        this.mes[2][1] = this.segundaTresLabel;
        this.mes[2][2] = this.tercaTresLabel;
        this.mes[2][3] = this.quartaTresLabel;
        this.mes[2][4] = this.quintaTresLabel;
        this.mes[2][5] = this.sextaTresLabel;
        this.mes[2][6] = this.sabadoTresLabel;
    }
    
    public void montaQuartaSemana() {
        this.mes[3][0] = this.domingoQuatroLabel;
        this.mes[3][1] = this.segundaQuatroLabel;
        this.mes[3][2] = this.tercaQuatroLabel;
        this.mes[3][3] = this.quartaQuatroLabel;
        this.mes[3][4] = this.quintaQuatroLabel;
        this.mes[3][5] = this.sextaQuatroLabel;
        this.mes[3][6] = this.sabadoQuatroLabel;
    }
    
    public void montaQuintaSemana() {
        this.mes[4][0] = this.domingoCincoLabel;
        this.mes[4][1] = this.segundaCincoLabel;
        this.mes[4][2] = this.tercaCincoLabel;
        this.mes[4][3] = this.quartaCincoLabel;
        this.mes[4][4] = this.quintaCincoLabel;
        this.mes[4][5] = this.sextaCincoLabel;
        this.mes[4][6] = this.sabadoCincoLabel;
    }
    
    public void montaSextaSemana() {
        this.mes[5][0] = this.domingoSeisLabel;
        this.mes[5][1] = this.segundaSeisLabel;
        this.mes[5][2] = this.tercaSeisLabel;
        this.mes[5][3] = this.quartaSeisLabel;
        this.mes[5][4] = this.quintaSeisLabel;
        this.mes[5][5] = this.sextaSeisLabel;
        this.mes[5][6] = this.sabadoSeisLabel;
    }
    
    public void actionPerformed(ActionEvent evento) {
        if (evento.getActionCommand().equals(this.mesAnteriorBt.getActionCommand())) this.mesAnteriorBtClicked();
        else if (evento.getActionCommand().equals(this.proximoMesBt.getActionCommand())) this.proximoMesBtClicked();
        else if (evento.getActionCommand().equals(this.anoAnteriorBt.getActionCommand())) this.anoAnteriorBtClicked();
        else if (evento.getActionCommand().equals(this.proximoAnoBt.getActionCommand())) this.proximoAnoBtClicked();
    }

    public JButton getAnoAnteriorBt() {
        return anoAnteriorBt;
    }
    
    public void proximoAnoBtClicked() {
        this.calendar.irParaProximoAno();
        this.mostraMes(this.calendar.diasDoMes());
    }
    
    public void anoAnteriorBtClicked() {
        this.calendar.irParaAnoAnterior();
        this.mostraMes(this.calendar.diasDoMes());
    }
    
    public void mesAnteriorBtClicked() {
        this.calendar.irParaMesAnterior();
        this.mostraMes(this.calendar.diasDoMes());
    }
    
    public void proximoMesBtClicked() {
        this.calendar.irParaProximoMes();
        this.mostraMes(this.calendar.diasDoMes());
    }
    
    public void update(Observable observable, Object objetoSelecionado) {
        this.objetoSelecionado = objetoSelecionado;
        for (JLabel[] labels : mes) {
            for(JLabel label : labels)
                ((DayView)label).update(observable, this.objetoSelecionado);
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jPanel1 = new javax.swing.JPanel();
        domingoLabel = new javax.swing.JLabel();
        segundaLabel = new javax.swing.JLabel();
        tercaLabel = new javax.swing.JLabel();
        quartaLabel = new javax.swing.JLabel();
        quintaLabel = new javax.swing.JLabel();
        sextaLabel = new javax.swing.JLabel();
        sabadoLabel = new javax.swing.JLabel();
        domingoUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        segundaUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        tercaUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quartaUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quintaUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sextaUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sabadoUmLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        domingoDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        segundaDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        tercaDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quartaDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quintaDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sextaDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sabadoDoisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        domingoTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        segundaTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        tercaTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quartaTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quintaTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sextaTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sabadoTresLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        domingoQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        segundaQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        tercaQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quartaQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quintaQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sextaQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sabadoQuatroLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        domingoCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        segundaCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        tercaCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quartaCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quintaCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sextaCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sabadoCincoLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        domingoSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        segundaSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        tercaSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quartaSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        quintaSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sextaSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        sabadoSeisLabel = new DayView(this.consultasParaConversaDoDia, this.conversasDoDiaView);
        jPanel2 = new javax.swing.JPanel();
        mesLabel = new javax.swing.JLabel();
        anoLabel = new javax.swing.JLabel();
        mesAnteriorBt = new javax.swing.JButton();
        this.mesAnteriorBt.addActionListener(this);
        proximoMesBt = new javax.swing.JButton();
        this.proximoMesBt.addActionListener(this);
        anoAnteriorBt = new javax.swing.JButton();
        this.anoAnteriorBt.addActionListener(this);
        proximoAnoBt = new javax.swing.JButton();
        this.proximoAnoBt.addActionListener(this);

        setBackground(new java.awt.Color(255, 255, 255));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        jPanel1.setLayout(new java.awt.GridLayout(7, 7));

        domingoLabel.setBackground(new java.awt.Color(204, 204, 204));
        domingoLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        domingoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        domingoLabel.setText("Dom");
        domingoLabel.setAlignmentY(0.0F);
        domingoLabel.setIconTextGap(2);
        domingoLabel.setOpaque(true);
        jPanel1.add(domingoLabel);

        segundaLabel.setBackground(new java.awt.Color(204, 204, 204));
        segundaLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        segundaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        segundaLabel.setText("Seg");
        segundaLabel.setAlignmentY(0.0F);
        segundaLabel.setIconTextGap(2);
        segundaLabel.setOpaque(true);
        jPanel1.add(segundaLabel);

        tercaLabel.setBackground(new java.awt.Color(204, 204, 204));
        tercaLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        tercaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tercaLabel.setText("Ter");
        tercaLabel.setAlignmentY(0.0F);
        tercaLabel.setIconTextGap(2);
        tercaLabel.setOpaque(true);
        jPanel1.add(tercaLabel);

        quartaLabel.setBackground(new java.awt.Color(204, 204, 204));
        quartaLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        quartaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quartaLabel.setText("Qua");
        quartaLabel.setAlignmentY(0.0F);
        quartaLabel.setIconTextGap(2);
        quartaLabel.setOpaque(true);
        jPanel1.add(quartaLabel);

        quintaLabel.setBackground(new java.awt.Color(204, 204, 204));
        quintaLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        quintaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        quintaLabel.setText("Qui");
        quintaLabel.setAlignmentY(0.0F);
        quintaLabel.setIconTextGap(2);
        quintaLabel.setOpaque(true);
        jPanel1.add(quintaLabel);

        sextaLabel.setBackground(new java.awt.Color(204, 204, 204));
        sextaLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        sextaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sextaLabel.setText("Sex");
        sextaLabel.setAlignmentY(0.0F);
        sextaLabel.setIconTextGap(2);
        sextaLabel.setOpaque(true);
        jPanel1.add(sextaLabel);

        sabadoLabel.setBackground(new java.awt.Color(204, 204, 204));
        sabadoLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        sabadoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sabadoLabel.setText("Sab");
        sabadoLabel.setAlignmentY(0.0F);
        sabadoLabel.setIconTextGap(2);
        sabadoLabel.setOpaque(true);
        jPanel1.add(sabadoLabel);

        domingoUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        domingoUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        domingoUmLabel.setAlignmentY(0.0F);
        domingoUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        domingoUmLabel.setEnabled(false);
        domingoUmLabel.setIconTextGap(2);
        domingoUmLabel.setOpaque(true);
        jPanel1.add(domingoUmLabel);

        segundaUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        segundaUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        segundaUmLabel.setAlignmentY(0.0F);
        segundaUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        segundaUmLabel.setEnabled(false);
        segundaUmLabel.setIconTextGap(2);
        segundaUmLabel.setOpaque(true);
        jPanel1.add(segundaUmLabel);

        tercaUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tercaUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tercaUmLabel.setAlignmentY(0.0F);
        tercaUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tercaUmLabel.setEnabled(false);
        tercaUmLabel.setIconTextGap(2);
        tercaUmLabel.setOpaque(true);
        jPanel1.add(tercaUmLabel);

        quartaUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quartaUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quartaUmLabel.setAlignmentY(0.0F);
        quartaUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quartaUmLabel.setEnabled(false);
        quartaUmLabel.setIconTextGap(2);
        quartaUmLabel.setOpaque(true);
        jPanel1.add(quartaUmLabel);

        quintaUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quintaUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quintaUmLabel.setAlignmentY(0.0F);
        quintaUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quintaUmLabel.setEnabled(false);
        quintaUmLabel.setIconTextGap(2);
        quintaUmLabel.setOpaque(true);
        jPanel1.add(quintaUmLabel);

        sextaUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sextaUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sextaUmLabel.setAlignmentY(0.0F);
        sextaUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sextaUmLabel.setEnabled(false);
        sextaUmLabel.setIconTextGap(2);
        sextaUmLabel.setOpaque(true);
        jPanel1.add(sextaUmLabel);

        sabadoUmLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sabadoUmLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sabadoUmLabel.setAlignmentY(0.0F);
        sabadoUmLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sabadoUmLabel.setEnabled(false);
        sabadoUmLabel.setIconTextGap(2);
        sabadoUmLabel.setOpaque(true);
        jPanel1.add(sabadoUmLabel);

        domingoDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        domingoDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        domingoDoisLabel.setAlignmentY(0.0F);
        domingoDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        domingoDoisLabel.setEnabled(false);
        domingoDoisLabel.setIconTextGap(2);
        domingoDoisLabel.setOpaque(true);
        jPanel1.add(domingoDoisLabel);

        segundaDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        segundaDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        segundaDoisLabel.setAlignmentY(0.0F);
        segundaDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        segundaDoisLabel.setEnabled(false);
        segundaDoisLabel.setIconTextGap(2);
        segundaDoisLabel.setOpaque(true);
        jPanel1.add(segundaDoisLabel);

        tercaDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tercaDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tercaDoisLabel.setAlignmentY(0.0F);
        tercaDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tercaDoisLabel.setEnabled(false);
        tercaDoisLabel.setIconTextGap(2);
        tercaDoisLabel.setOpaque(true);
        jPanel1.add(tercaDoisLabel);

        quartaDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quartaDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quartaDoisLabel.setAlignmentY(0.0F);
        quartaDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quartaDoisLabel.setEnabled(false);
        quartaDoisLabel.setIconTextGap(2);
        quartaDoisLabel.setOpaque(true);
        jPanel1.add(quartaDoisLabel);

        quintaDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quintaDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quintaDoisLabel.setAlignmentY(0.0F);
        quintaDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quintaDoisLabel.setEnabled(false);
        quintaDoisLabel.setIconTextGap(2);
        quintaDoisLabel.setOpaque(true);
        jPanel1.add(quintaDoisLabel);

        sextaDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sextaDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sextaDoisLabel.setAlignmentY(0.0F);
        sextaDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sextaDoisLabel.setEnabled(false);
        sextaDoisLabel.setIconTextGap(2);
        sextaDoisLabel.setOpaque(true);
        jPanel1.add(sextaDoisLabel);

        sabadoDoisLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sabadoDoisLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sabadoDoisLabel.setAlignmentY(0.0F);
        sabadoDoisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sabadoDoisLabel.setEnabled(false);
        sabadoDoisLabel.setIconTextGap(2);
        sabadoDoisLabel.setOpaque(true);
        jPanel1.add(sabadoDoisLabel);

        domingoTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        domingoTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        domingoTresLabel.setAlignmentY(0.0F);
        domingoTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        domingoTresLabel.setEnabled(false);
        domingoTresLabel.setIconTextGap(2);
        domingoTresLabel.setOpaque(true);
        jPanel1.add(domingoTresLabel);

        segundaTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        segundaTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        segundaTresLabel.setAlignmentY(0.0F);
        segundaTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        segundaTresLabel.setEnabled(false);
        segundaTresLabel.setIconTextGap(2);
        segundaTresLabel.setOpaque(true);
        jPanel1.add(segundaTresLabel);

        tercaTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tercaTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tercaTresLabel.setAlignmentY(0.0F);
        tercaTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tercaTresLabel.setEnabled(false);
        tercaTresLabel.setIconTextGap(2);
        tercaTresLabel.setOpaque(true);
        jPanel1.add(tercaTresLabel);

        quartaTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quartaTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quartaTresLabel.setAlignmentY(0.0F);
        quartaTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quartaTresLabel.setEnabled(false);
        quartaTresLabel.setIconTextGap(2);
        quartaTresLabel.setOpaque(true);
        jPanel1.add(quartaTresLabel);

        quintaTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quintaTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quintaTresLabel.setAlignmentY(0.0F);
        quintaTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quintaTresLabel.setEnabled(false);
        quintaTresLabel.setIconTextGap(2);
        quintaTresLabel.setOpaque(true);
        jPanel1.add(quintaTresLabel);

        sextaTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sextaTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sextaTresLabel.setAlignmentY(0.0F);
        sextaTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sextaTresLabel.setEnabled(false);
        sextaTresLabel.setIconTextGap(2);
        sextaTresLabel.setOpaque(true);
        jPanel1.add(sextaTresLabel);

        sabadoTresLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sabadoTresLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sabadoTresLabel.setAlignmentY(0.0F);
        sabadoTresLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sabadoTresLabel.setEnabled(false);
        sabadoTresLabel.setIconTextGap(2);
        sabadoTresLabel.setOpaque(true);
        jPanel1.add(sabadoTresLabel);

        domingoQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        domingoQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        domingoQuatroLabel.setAlignmentY(0.0F);
        domingoQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        domingoQuatroLabel.setEnabled(false);
        domingoQuatroLabel.setIconTextGap(2);
        domingoQuatroLabel.setOpaque(true);
        jPanel1.add(domingoQuatroLabel);

        segundaQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        segundaQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        segundaQuatroLabel.setAlignmentY(0.0F);
        segundaQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        segundaQuatroLabel.setEnabled(false);
        segundaQuatroLabel.setIconTextGap(2);
        segundaQuatroLabel.setOpaque(true);
        jPanel1.add(segundaQuatroLabel);

        tercaQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tercaQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tercaQuatroLabel.setAlignmentY(0.0F);
        tercaQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tercaQuatroLabel.setEnabled(false);
        tercaQuatroLabel.setIconTextGap(2);
        tercaQuatroLabel.setOpaque(true);
        jPanel1.add(tercaQuatroLabel);

        quartaQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quartaQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quartaQuatroLabel.setAlignmentY(0.0F);
        quartaQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quartaQuatroLabel.setEnabled(false);
        quartaQuatroLabel.setIconTextGap(2);
        quartaQuatroLabel.setOpaque(true);
        jPanel1.add(quartaQuatroLabel);

        quintaQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quintaQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quintaQuatroLabel.setAlignmentY(0.0F);
        quintaQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quintaQuatroLabel.setEnabled(false);
        quintaQuatroLabel.setIconTextGap(2);
        quintaQuatroLabel.setOpaque(true);
        jPanel1.add(quintaQuatroLabel);

        sextaQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sextaQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sextaQuatroLabel.setAlignmentY(0.0F);
        sextaQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sextaQuatroLabel.setEnabled(false);
        sextaQuatroLabel.setIconTextGap(2);
        sextaQuatroLabel.setOpaque(true);
        jPanel1.add(sextaQuatroLabel);

        sabadoQuatroLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sabadoQuatroLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sabadoQuatroLabel.setAlignmentY(0.0F);
        sabadoQuatroLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sabadoQuatroLabel.setEnabled(false);
        sabadoQuatroLabel.setIconTextGap(2);
        sabadoQuatroLabel.setOpaque(true);
        jPanel1.add(sabadoQuatroLabel);

        domingoCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        domingoCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        domingoCincoLabel.setAlignmentY(0.0F);
        domingoCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        domingoCincoLabel.setEnabled(false);
        domingoCincoLabel.setIconTextGap(2);
        domingoCincoLabel.setOpaque(true);
        jPanel1.add(domingoCincoLabel);

        segundaCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        segundaCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        segundaCincoLabel.setAlignmentY(0.0F);
        segundaCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        segundaCincoLabel.setEnabled(false);
        segundaCincoLabel.setIconTextGap(2);
        segundaCincoLabel.setOpaque(true);
        jPanel1.add(segundaCincoLabel);

        tercaCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tercaCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        tercaCincoLabel.setAlignmentY(0.0F);
        tercaCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tercaCincoLabel.setEnabled(false);
        tercaCincoLabel.setIconTextGap(2);
        tercaCincoLabel.setOpaque(true);
        jPanel1.add(tercaCincoLabel);

        quartaCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quartaCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quartaCincoLabel.setAlignmentY(0.0F);
        quartaCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quartaCincoLabel.setEnabled(false);
        quartaCincoLabel.setIconTextGap(2);
        quartaCincoLabel.setOpaque(true);
        jPanel1.add(quartaCincoLabel);

        quintaCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        quintaCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        quintaCincoLabel.setAlignmentY(0.0F);
        quintaCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quintaCincoLabel.setEnabled(false);
        quintaCincoLabel.setIconTextGap(2);
        quintaCincoLabel.setOpaque(true);
        jPanel1.add(quintaCincoLabel);

        sextaCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sextaCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sextaCincoLabel.setAlignmentY(0.0F);
        sextaCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sextaCincoLabel.setEnabled(false);
        sextaCincoLabel.setIconTextGap(2);
        sextaCincoLabel.setOpaque(true);
        jPanel1.add(sextaCincoLabel);

        sabadoCincoLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        sabadoCincoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        sabadoCincoLabel.setAlignmentY(0.0F);
        sabadoCincoLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sabadoCincoLabel.setEnabled(false);
        sabadoCincoLabel.setIconTextGap(2);
        sabadoCincoLabel.setOpaque(true);
        jPanel1.add(sabadoCincoLabel);

        domingoSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        domingoSeisLabel.setEnabled(false);
        domingoSeisLabel.setOpaque(true);
        jPanel1.add(domingoSeisLabel);

        segundaSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        segundaSeisLabel.setOpaque(true);
        jPanel1.add(segundaSeisLabel);

        tercaSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tercaSeisLabel.setOpaque(true);
        jPanel1.add(tercaSeisLabel);

        quartaSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quartaSeisLabel.setOpaque(true);
        jPanel1.add(quartaSeisLabel);

        quintaSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        quintaSeisLabel.setOpaque(true);
        jPanel1.add(quintaSeisLabel);

        sextaSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sextaSeisLabel.setOpaque(true);
        jPanel1.add(sextaSeisLabel);

        sabadoSeisLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sabadoSeisLabel.setOpaque(true);
        jPanel1.add(sabadoSeisLabel);

        mesLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        mesLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        anoLabel.setFont(new java.awt.Font("Tahoma", 1, 11));
        anoLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        mesAnteriorBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nav_left_blue.png")));
        mesAnteriorBt.setActionCommand("mesAnteriorBt");
        mesAnteriorBt.setBorderPainted(false);
        mesAnteriorBt.setContentAreaFilled(false);
        mesAnteriorBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        proximoMesBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nav_right_blue.png")));
        proximoMesBt.setActionCommand("proximoMesBt");
        proximoMesBt.setBorderPainted(false);
        proximoMesBt.setContentAreaFilled(false);
        proximoMesBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        anoAnteriorBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nav_left_blue.png")));
        anoAnteriorBt.setActionCommand("anoAnteriorBt");
        anoAnteriorBt.setBorderPainted(false);
        anoAnteriorBt.setContentAreaFilled(false);
        anoAnteriorBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        proximoAnoBt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/nav_right_blue.png")));
        proximoAnoBt.setActionCommand("proximoAnoBt");
        proximoAnoBt.setBorderPainted(false);
        proximoAnoBt.setContentAreaFilled(false);
        proximoAnoBt.setMargin(new java.awt.Insets(2, 2, 2, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mesAnteriorBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proximoMesBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(anoAnteriorBt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(anoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proximoAnoBt)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mesAnteriorBt)
                    .addComponent(proximoMesBt)
                    .addComponent(anoAnteriorBt)
                    .addComponent(proximoAnoBt)
                    .addComponent(mesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton anoAnteriorBt;
    private javax.swing.JLabel anoLabel;
    private javax.swing.JLabel domingoCincoLabel;
    private javax.swing.JLabel domingoDoisLabel;
    private javax.swing.JLabel domingoLabel;
    private javax.swing.JLabel domingoQuatroLabel;
    private javax.swing.JLabel domingoSeisLabel;
    private javax.swing.JLabel domingoTresLabel;
    private javax.swing.JLabel domingoUmLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton mesAnteriorBt;
    private javax.swing.JLabel mesLabel;
    private javax.swing.JButton proximoAnoBt;
    private javax.swing.JButton proximoMesBt;
    private javax.swing.JLabel quartaCincoLabel;
    private javax.swing.JLabel quartaDoisLabel;
    private javax.swing.JLabel quartaLabel;
    private javax.swing.JLabel quartaQuatroLabel;
    private javax.swing.JLabel quartaSeisLabel;
    private javax.swing.JLabel quartaTresLabel;
    private javax.swing.JLabel quartaUmLabel;
    private javax.swing.JLabel quintaCincoLabel;
    private javax.swing.JLabel quintaDoisLabel;
    private javax.swing.JLabel quintaLabel;
    private javax.swing.JLabel quintaQuatroLabel;
    private javax.swing.JLabel quintaSeisLabel;
    private javax.swing.JLabel quintaTresLabel;
    private javax.swing.JLabel quintaUmLabel;
    private javax.swing.JLabel sabadoCincoLabel;
    private javax.swing.JLabel sabadoDoisLabel;
    private javax.swing.JLabel sabadoLabel;
    private javax.swing.JLabel sabadoQuatroLabel;
    private javax.swing.JLabel sabadoSeisLabel;
    private javax.swing.JLabel sabadoTresLabel;
    private javax.swing.JLabel sabadoUmLabel;
    private javax.swing.JLabel segundaCincoLabel;
    private javax.swing.JLabel segundaDoisLabel;
    private javax.swing.JLabel segundaLabel;
    private javax.swing.JLabel segundaQuatroLabel;
    private javax.swing.JLabel segundaSeisLabel;
    private javax.swing.JLabel segundaTresLabel;
    private javax.swing.JLabel segundaUmLabel;
    private javax.swing.JLabel sextaCincoLabel;
    private javax.swing.JLabel sextaDoisLabel;
    private javax.swing.JLabel sextaLabel;
    private javax.swing.JLabel sextaQuatroLabel;
    private javax.swing.JLabel sextaSeisLabel;
    private javax.swing.JLabel sextaTresLabel;
    private javax.swing.JLabel sextaUmLabel;
    private javax.swing.JLabel tercaCincoLabel;
    private javax.swing.JLabel tercaDoisLabel;
    private javax.swing.JLabel tercaLabel;
    private javax.swing.JLabel tercaQuatroLabel;
    private javax.swing.JLabel tercaSeisLabel;
    private javax.swing.JLabel tercaTresLabel;
    private javax.swing.JLabel tercaUmLabel;
    // End of variables declaration//GEN-END:variables
}