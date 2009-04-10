package com.agnaldo4j.prm.clock;

import java.util.Calendar;
import java.util.List;

public interface CalendarWrapper {
    public List<Calendar> diasDoMes();
    public void irParaProximoMes();
    public void irParaMesAnterior();
    public void irParaProximoAno();
    public void irParaAnoAnterior();
}