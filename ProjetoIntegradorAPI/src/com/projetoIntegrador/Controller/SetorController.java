package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.SetorDAL;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.SetorModel;
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
	
}
