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
import com.projetoIntegrador.ViewModel.Retorno;
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
			retorno.Mensagem = "Falha ao realizar a listagem de solicitações de viagem.";
		}
		
		return retorno;
	}

	@POST
	@Path("/salvar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Salvar(SolicitacaoViagemViewModel solicitacao)
	{		
		Retorno retorno = new Retorno();
		
		try {
			
			SolicitacaoViagemModel model = new SolicitacaoViagemModel(solicitacao);
			
			int id = SolicitacaoViagemDAL.Inserir(model);
			
			
			retorno.Sucesso = true;
			
		} catch (Exception e) 
		{
			retorno.Mensagem = "Falha ao realizar ao salvar a solicitação de viagem.";
		}
		
		return retorno;
	}
	
	@POST
	@Path("/remover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Remover(String id)
	{		
		Retorno retorno = new Retorno();
		
		try {
			if (SolicitacaoViagemDAL.Deleter(Integer.parseInt(id)))
			{
				retorno.Sucesso = true;
			}
			else
			{
				retorno.Mensagem = "A solicitação de viagem a ser removida não foi encontrada.";
			}			
			
		} catch (Exception e) 
		{
			retorno.Mensagem = "Falha ao realizar a removção de solicitação de viagem.";
		}
		
		return retorno;
	}
	
}
