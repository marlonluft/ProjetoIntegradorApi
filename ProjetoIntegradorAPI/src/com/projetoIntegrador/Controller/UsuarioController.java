package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Model.UsuarioModel;
import com.projetoIntegrador.ViewModel.LoginViewModel;
import com.projetoIntegrador.ViewModel.Retorno;
import com.projetoIntegrador.ViewModel.UsuarioViewModel;

@Path("/usuario")
public class UsuarioController 
{	
	@POST
	@Path("/logar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginViewModel Logar(LoginViewModel model) 
	{
		LoginViewModel retorno = new LoginViewModel();

		try {
			UsuarioModel usuario = UsuarioDAL.VerificarLogin(model.Email, model.Senha);
			
			if (usuario != null) 
			{
				retorno.Sucesso = true;
				retorno.Cargo = usuario.getCargo();
				retorno.Id = usuario.getId();
			}
			else
			{
				retorno.Mensagem = retorno.Sucesso ? "" : "Usuário e/ou senha inválido, tente novamente.";
			}
		} 
		catch (Exception e) 
		{
			retorno.Mensagem = "Erro ao realizar o logn, tente novamente. Erro: " + e.getMessage();
		}

		return retorno;
	}
	
	@POST
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioViewModel Listar()
	{		
		List<UsuarioModel> lista = UsuarioDAL.Listar();
		UsuarioViewModel retorno = new UsuarioViewModel(lista);
		retorno.Sucesso = true;
		
		return retorno;
	}
	
	@POST
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioViewModel Consultar(UsuarioViewModel model)
	{
		UsuarioModel usuario = UsuarioDAL.Buscar(model.Id);
		return new UsuarioViewModel(usuario);
	}

	@POST
	@Path("/manipular")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Manipular (UsuarioViewModel model)
	{
		Retorno retorno = new Retorno();
		
		try 
		{
			UsuarioModel usuario = new UsuarioModel(model);
			
			if (usuario.getId() >= 0) 
			{
				retorno.Sucesso = UsuarioDAL.Inserir(usuario) >= 0;
			}
			else
			{
				retorno.Sucesso = UsuarioDAL.Alterar(usuario);
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
	public Retorno Remover (Integer id)
	{
		Retorno retorno = new Retorno();
		
		try 
		{
			retorno.Sucesso = UsuarioDAL.Deleter(id);
			
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