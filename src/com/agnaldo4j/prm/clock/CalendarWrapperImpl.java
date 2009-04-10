package com.agnaldo4j.prm.clock;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CalendarWrapperImpl implements CalendarWrapper {
    private Calendar calendar;
        
    public CalendarWrapperImpl(Calendar calendar) {
        this.calendar = calendar;
    }
    
    public void irParaProximoAno() {
        this.calendar.set(Calendar.YEAR, this.calendar.get(Calendar.YEAR) + 1);
    }
    
    public void irParaAnoAnterior() {
        this.calendar.set(Calendar.YEAR, this.calendar.get(Calendar.YEAR) - 1);
    }
    
    public void irParaProximoMes() {
        this.calendar.set(Calendar.DAY_OF_MONTH, 1);
        this.calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH) + 1);
    }
    
    public void irParaMesAnterior() {
        this.calendar.set(Calendar.DAY_OF_MONTH, 1);
        this.calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH) - 1);
    }
    
    public List<Calendar> diasDoMes() {
        List<Calendar> diasDoMes = new LinkedList<Calendar>();
        int mes = this.calendar.get(Calendar.MONTH);
        for (int i = 1; i < 32; i++) {
            this.calendar.set(Calendar.DATE, i);
            if (mes == this.calendar.get(Calendar.MONTH)) {
                GregorianCalendar novo = new GregorianCalendar(Locale.ENGLISH);
                novo.setTime(this.calendar.getTime());
                diasDoMes.add(novo);                
            } else {
                this.calendar.set(Calendar.MONTH, this.calendar.get(Calendar.MONTH) - 1);
                break;
            }
        }
        return diasDoMes;
    }
}