package com.projetoIntegrador.DAL;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;

public class SolicitacaoViagemDAL {

	public static List<SolicitacaoViagemModel> Listar() throws BDException, ParseException {
		List<SolicitacaoViagemModel> lista = new ArrayList<SolicitacaoViagemModel>();
		
		for (int i = 0; i < 10; i++) {
			
			SolicitacaoViagemModel model = new SolicitacaoViagemModel();
			model.setCidadeDestino("CidadeDestino"+i);
			model.setCidadeOrigem("CidadeOrigem"+i);
			model.setId(i);
			model.setIdUsuario(i+1);
			model.setMotivo("Motivo");
			model.setObservacao("Observacao");			
			model.setUfDestino("SC");
			model.setUfOrigem("RS");	
			
			if (i <= 1) {
				model.setStatus(EStatus.AGUARDANDO_APROVACAO_CONTAS);
			}
			else if (i > 3)
			{
				model.setStatus(EStatus.AGUARDANDO_APROVACAO_VIAGEM);
			}
			else if (i == 6)
			{
				model.setStatus(EStatus.EM_ABERTO);
			}
			else if (i == 7)
			{
				model.setStatus(EStatus.FINALIZADO);
			}
			else if (i == 8)
			{
				model.setStatus(EStatus.RECUSADO_VIAGEM);
			}
			else if (i == 2)
			{
				model.setStatus(EStatus.EM_ABERTO_CONTAS);
			}
			else
			{
				model.setStatus(EStatus.RECUSADO_CONTAS);				
			}
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			model.setDataIda(formatter.parse("0" + (i+1) + "/01/2007"));
			
			model.setDataVolta(formatter.parse("0" + (i+3) + "/01/2007"));
			
			lista.add(model);
		}
		
		return lista;
	}

}
