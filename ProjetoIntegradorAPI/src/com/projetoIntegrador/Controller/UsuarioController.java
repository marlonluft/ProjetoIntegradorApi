package com.projetoIntegrador.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Model.UsuarioModel;
import com.projetoIntegrador.ViewModel.LoginViewModel;
import com.projetoIntegrador.ViewModel.Retorno;

@Path("/usuario")
public class UsuarioController 
{
	/*Método GET Exemplo
	@GET
	 @Produces(MediaType.TEXT_PLAIN)
	 @Path("/nome/{name}")
	 public String getCelcius(@Context HttpHeaders headers, @PathParam("name") String name){
	 return "Hello "+name;
	 }*/
	
	
	@POST
	@Path("/logar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginViewModel post(LoginViewModel model) {
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
		} catch (Exception e) {
			retorno.Mensagem = "Erro ao realizar o logn, tente novamente. Erro: " + e.getMessage();
		}

		return retorno;
	}

}