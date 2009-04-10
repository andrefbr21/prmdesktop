package com.agnaldo4j.prm.ui.calendar.query;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.Relacionamento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConsultaConversasDoRelacionamento implements ConsultaConversas {
    
    private Object lock = new Object();
    
    public ConsultaConversasDoRelacionamento() {}

    public Map<Relacionamento, List<Conversa>> recuperaConversasDoDia(Calendar calendar, Object objetoSelecionado) {
        synchronized (lock) {
           Map<Relacionamento, List<Conversa>> retorno = new HashMap<Relacionamento, List<Conversa>>(); 
            if (objetoSelecionado.getClass() == Relacionamento.class) {
                Relacionamento relacionamento = (Relacionamento) objetoSelecionado;
                Set<Conversa> conversas = relacionamento.getConversas();
                List<Conversa> listaDeConversas = new ArrayList<Conversa>();
                for(Conversa conversa : conversas) {
                    GregorianCalendar conversaData = new GregorianCalendar();
                    conversaData.setTime(conversa.getData());
                    if (conversaData.get(Calendar.YEAR) == calendar.get(Calendar.YEAR))
                        if (conversaData.get(Calendar.MONTH) == calendar.get(Calendar.MONTH))
                            if (conversaData.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)) {
                                listaDeConversas.add(conversa);
                                if (!retorno.containsKey(relacionamento)) retorno.put(relacionamento, listaDeConversas);
                            }
                }
            }
            return retorno;
        }
    }
    
}
