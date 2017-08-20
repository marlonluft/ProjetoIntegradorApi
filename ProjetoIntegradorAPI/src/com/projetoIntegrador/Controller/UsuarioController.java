package com.projetoIntegrador.Controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.UsuarioDAL;
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
	public Retorno post(LoginViewModel model) {
		Retorno retorno = new Retorno();

		try {
			retorno.Sucesso = UsuarioDAL.VerificarLogin(model.Email, model.Senha);

			retorno.Mensagem = retorno.Sucesso ? "" : "Usuário ou senha inválido, tente novamente.";

		} catch (Exception e) {
			retorno.Mensagem = "Erro ao realizar o logn, tente novamente. Erro: " + e.getMessage();
		}

		return retorno;
	}

}