package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;

import com.projetoIntegrador.DAL.SolicitacaoViagemDAL;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;
import com.projetoIntegrador.ViewModel.SolicitacaoViagemViewModel;

@Path("/solicitacao")
public class SolicitacaoController {

	@POST
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoViagemViewModel Listar(@XmlElement int idUsuario)
	{		
		SolicitacaoViagemViewModel retorno;
		
		try {
			List<SolicitacaoViagemModel> lista = SolicitacaoViagemDAL.Listar(idUsuario);
			retorno = new SolicitacaoViagemViewModel(lista);
			retorno.Sucesso = true;
			
		} catch (Exception e) 
		{
			retorno = new SolicitacaoViagemViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de usuários.";
		}
		
		return retorno;
	}
	
}
