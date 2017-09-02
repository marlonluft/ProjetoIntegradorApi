package com.projetoIntegrador.DAL;

import java.util.ArrayList;
import java.util.List;

import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;

public class SolicitacaoViagemDAL {

	public static List<SolicitacaoViagemModel> Listar() throws BDException {
		List<SolicitacaoViagemModel> lista = new ArrayList<SolicitacaoViagemModel>();
		
		for (int i = 0; i < 10; i++) {
			
			SolicitacaoViagemModel model = new SolicitacaoViagemModel();
			model.setCidadeDestino("CidadeDestino"+i);
			model.setCidadeOrigem("CidadeOrigem"+i);
			model.setId(i);
			model.setIdUsuario(i+1);
			model.setMotivo("Motivo");
			model.setObservacao("Observacao");
			model.setStatus(EStatus.EM_ABERTO);
			model.setUfDestino("SC");
			model.setUfOrigem("RS");			
			
			lista.add(model);
		}
		
		return lista;
	}

}
