package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.SetorDAL;
import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Enumerador.EPerfil;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.SetorModel;
import com.projetoIntegrador.Model.UsuarioModel;
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
			SetorModel setorModel = new SetorModel(model);
			
			UsuarioModel usuario = UsuarioDAL.Buscar(setorModel.getIdUsuario());
			
			if (usuario == null || usuario.getPerfil() != EPerfil.GESTOR)
			{
				retorno.Mensagem = "O gestor selecionado não existe ou não contém o cargo de gestor.";
			}
			else
			{	
				if (setorModel.getId() >= 0) 
				{
					SetorModel setor;
					
					try
					{
						setor = SetorDAL.Buscar(model.Id);
					}
					catch (Exception e) {
						setor = null;
					}
					
					if (setor == null)
					{
						retorno.Mensagem = "Setor não encontrado para atualizar.";
					}
					else if (!setor.getNome().equals(setorModel.getNome()) && SetorDAL.Existe(setorModel.getNome()))
					{
						retorno.Mensagem = "Já existe um setor com este nome.";
					}
					else
					{
						retorno.Sucesso = SetorDAL.Alterar(setorModel);
					}
				}
				else
				{				
					if (SetorDAL.Existe(setorModel.getNome()))
					{
						retorno.Mensagem = "Já existe um setor com este nome.";
					}
					else
					{
						retorno.Sucesso = SetorDAL.Inserir(setorModel) >= 0;
					}					
				}
			}
			
			if (!retorno.Sucesso && (retorno.Mensagem == null || retorno.Mensagem.length() == 0)) {
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
			SetorModel setor;
			
			try
			{
				setor = SetorDAL.Buscar(model.Id);
			}
			catch (Exception e) {
				setor = null;
			}
			
			if (setor == null)
			{
				retorno.Mensagem = "Setor não encontrado.";
			}
			else if (UsuarioDAL.GetQuantidadeColaboradores(model.Id) > 0)
			{
				retorno.Mensagem = "Este setor não pode ser removido pois há colaboradores vinculados a ele.";
			}			
			else
			{			
				retorno.Sucesso = SetorDAL.Deleter(model.Id);
			}
			
			if (!retorno.Sucesso && (retorno.Mensagem == null || retorno.Mensagem.length() == 0)) {
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
