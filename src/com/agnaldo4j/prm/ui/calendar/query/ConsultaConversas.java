package com.agnaldo4j.prm.ui.calendar.query;

import com.agnaldo4j.bo.crm.Conversa;
import com.agnaldo4j.bo.crm.Relacionamento;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public interface ConsultaConversas {
    public Map<Relacionamento, List<Conversa>> recuperaConversasDoDia(Calendar calendar, Object objetoSelecionado);
}
