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
			UsuarioModel usuario = UsuarioDAL.VerificarLogin(model.CPF, model.Senha);
			
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
			UsuarioModel usuarioModel = new UsuarioModel(model);
			
			if (!SetorDAL.Existe(usuarioModel.getCodSetor()))
			{
				retorno.Mensagem = "O setor selecionado não existe";
			}
			else
			{			
				if (usuarioModel.getId() >= 0) 
				{
					UsuarioModel usuario;
				
					try
					{
						usuario = UsuarioDAL.Buscar(model.Id);
					}
					catch (Exception e) {
						usuario = null;
					}
				
					if (usuario == null)
					{
						retorno.Mensagem = "Usuário não encontrado";
					}
					else if (usuario.getPerfil() == EPerfil.ADMINISTRADOR && 
							usuarioModel.getPerfil() != EPerfil.ADMINISTRADOR &&
							UsuarioDAL.GetQuantidadeAdministradores() == 1
							)
					{				
						retorno.Mensagem = "Não é possível a alterar o perfil deste usuário, pois o mesmo é único administrador no sistema.";
					}
					else
					{
						if (!usuario.getEmail().equals(usuarioModel.getEmail()) && UsuarioDAL.Existe(usuarioModel.getEmail()))
						{
							retorno.Mensagem = "E-mail já vinculado a outro usuário.";
						}
						else if (!usuario.getCpf().equals(usuarioModel.getCpf()) && UsuarioDAL.ExisteCPF(usuarioModel.getCpf()))
						{
							retorno.Mensagem = "CPF já vinculado a outro usuário.";							
						}
						else
						{
							retorno.Sucesso = UsuarioDAL.Alterar(usuarioModel);
						}
					}
				}
				else
				{				
					if (UsuarioDAL.Existe(usuarioModel.getEmail()))
					{
						retorno.Mensagem = "E-mail já vinculado a outro usuário.";
					}
					else if (UsuarioDAL.ExisteCPF(usuarioModel.getCpf()))
					{
						retorno.Mensagem = "CPF já vinculado a outro usuário.";							
					}
					else
					{
						retorno.Sucesso = UsuarioDAL.Inserir(usuarioModel) >= 0;
					}
				}
			}
			
			if (!retorno.Sucesso && (retorno.Mensagem == null || retorno.Mensagem.length() == 0)) 
			{
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
			UsuarioModel usuario;
			
			try
			{
				usuario = UsuarioDAL.Buscar(model.Id);
			}
			catch (Exception e) 
			{
				usuario = null;
			}
			
			if (usuario != null) 
			{
				if (usuario.getPerfil() == EPerfil.GESTOR && usuario.getCodSetor() >= 0)
				{
					retorno.Mensagem = "Este gestor não pode ser removido pois está vinculado a um setor.";
				}
				else if (usuario.getPerfil() == EPerfil.ADMINISTRADOR && UsuarioDAL.GetQuantidadeAdministradores() == 1)
				{
					retorno.Mensagem = "Este administrado não pode ser removido pois ele é o único no sistema.";	
				}
				else
				{
					retorno.Sucesso = UsuarioDAL.Deleter(model.Id);					
				}
			}
			else
			{
				retorno.Mensagem = "Usuário não encontrado";
			}
			
			if (!retorno.Sucesso && (retorno.Mensagem == null || retorno.Mensagem.length() == 0)) 
			{
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