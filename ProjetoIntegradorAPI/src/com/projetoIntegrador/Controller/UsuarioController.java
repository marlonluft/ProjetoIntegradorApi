package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Exceptions.BDException;
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
				retorno.Perfil = usuario.getPerfil();
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
		UsuarioViewModel retorno;
		
		try {
			List<UsuarioModel> lista = UsuarioDAL.Listar();
			retorno = new UsuarioViewModel(lista);
			retorno.Sucesso = true;
			
		} catch (BDException e) 
		{
			retorno = new UsuarioViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de usuários.";
		}
		
		return retorno;
	}
	
	@POST
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioViewModel Consultar(UsuarioViewModel model)
	{
		UsuarioModel usuario;
		
		try 
		{
			usuario = UsuarioDAL.Buscar(model.Id);
			return new UsuarioViewModel(usuario);
		} 
		catch (BDException e) 
		{
			return new UsuarioViewModel();
		}		
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
				retorno.Sucesso = UsuarioDAL.Alterar(usuario);
			}
			else
			{				
				retorno.Sucesso = UsuarioDAL.Inserir(usuario) >= 0;
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
	public Retorno Remover (UsuarioViewModel model)
	{
		Retorno retorno = new Retorno();
		
		try 
		{
			retorno.Sucesso = UsuarioDAL.Deleter(model.Id);
			
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
	@Path("/listarGestores")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioViewModel ListarGestores()
	{		
		UsuarioViewModel retorno;
		
		try {
			List<UsuarioModel> lista = UsuarioDAL.Listar(true);
			retorno = new UsuarioViewModel(lista);
			retorno.Sucesso = true;
			
		} catch (BDException e) 
		{
			retorno = new UsuarioViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de usuários.";
		}
		
		return retorno;
	}
}