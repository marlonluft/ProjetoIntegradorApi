package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.SetorDAL;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.SetorModel;
import com.projetoIntegrador.ViewModel.Retorno;
import com.projetoIntegrador.ViewModel.SetorViewModel;

@Path("/setor")
public class SetorController {

	@POST
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public SetorViewModel Listar()
	{		
		SetorViewModel retorno;
		
		try {
			List<SetorModel> lista = SetorDAL.Listar();
			retorno = new SetorViewModel(lista);
			retorno.Sucesso = true;
			
		} catch (BDException e) 
		{
			retorno = new SetorViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de usuários.";
		}
		
		return retorno;
	}
	
	@POST
	@Path("/manipular")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Manipular (SetorViewModel model)
	{
		Retorno retorno = new Retorno();
		
		try 
		{
			SetorModel setor = new SetorModel(model);
			
			if (setor.getId() >= 0) 
			{
				retorno.Sucesso = SetorDAL.Alterar(setor);
			}
			else
			{				
				retorno.Sucesso = SetorDAL.Inserir(setor) >= 0;
			}
			
			if (!retorno.Sucesso) {
				retorno.Mensagem = "Houve um erro ao realiza a ação, favor contactar o suporte.";
			}
		
		} 
		catch (Exception e) 
		{
			retorno.Mensagem = "Houve um erro ao realiza a ação: " + e.getMessage();
		}
		
		return retorno;
	}
	
	@POST
	@Path("/remover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Remover (SetorViewModel model)
	{
		Retorno retorno = new Retorno();
		
		try 
		{
			retorno.Sucesso = SetorDAL.Deleter(model.Id);
			
			if (!retorno.Sucesso) {
				retorno.Mensagem = "Houve um erro ao realiza a ação, favor contactar o suporte.";
			}		
		} 
		catch (Exception e) 
		{
			retorno.Mensagem = "Houve um erro ao realiza a ação: " + e.getMessage();
		}
		
		return retorno;
	}
	
}
