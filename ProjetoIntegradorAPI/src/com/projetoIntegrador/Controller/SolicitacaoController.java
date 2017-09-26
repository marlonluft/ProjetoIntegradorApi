package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlElement;

import com.projetoIntegrador.DAL.SolicitacaoViagemDAL;
import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Enumerador.EPerfil;
import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;
import com.projetoIntegrador.Model.UsuarioModel;
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
			List<SolicitacaoViagemModel> lista = null;
			
			UsuarioModel usuario = UsuarioDAL.Buscar(idUsuario);
			if (usuario == null)
			{
				throw new Exception("Usu�rio logado n�o encontrado.");
			}
			else if (usuario.getPerfil() == EPerfil.GESTOR)
			{
				lista = SolicitacaoViagemDAL.Listar(idUsuario, true);
			}
			else
			{
				lista = SolicitacaoViagemDAL.Listar(idUsuario, false);
			}
			
			retorno = new SolicitacaoViagemViewModel(lista);
			retorno.Sucesso = true;
			
		} catch (Exception e) 
		{
			retorno = new SolicitacaoViagemViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de solicita��es de viagem.";
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
			
			if (model.getDataIda().getTime() > model.getDataVolta().getTime()) {
				retorno.Mensagem = "Data de Ida � maior que a Data de Volta.";
			}
			else
			{			
				if (model.getId() >= 0) {
					if (solicitacao.EnviarAprovacao) {
						model.setStatus(EStatus.AGUARDANDO_APROVACAO_VIAGEM);
					}
					else if (solicitacao.Aprovado) {
						model.setStatus(EStatus.EM_ABERTO_CONTAS);
					}
					else if (solicitacao.Reprovado) {
						model.setStatus(EStatus.RECUSADO_VIAGEM);
					}
					else if (solicitacao.EnviarAprovacaoCustos) {
						model.setStatus(EStatus.AGUARDANDO_APROVACAO_CONTAS);
					}
					else if (solicitacao.AprovadoCustos) {
						model.setStatus(EStatus.FINALIZADO);
					}
					else if (solicitacao.ReprovadoCustos) {
						model.setStatus(EStatus.RECUSADO_CONTAS);
					}
				
					SolicitacaoViagemDAL.Alterar(model);
				}
				else
				{
					if (solicitacao.EnviarAprovacao) 
					{
						model.setStatus(EStatus.AGUARDANDO_APROVACAO_VIAGEM);
					}
					else
					{
						model.setStatus(EStatus.EM_ABERTO);	
					}
							
					SolicitacaoViagemDAL.Inserir(model);
				}			
			
				retorno.Sucesso = true;			
			}
			
		} catch (Exception e) 
		{
			retorno.Mensagem = "Falha ao realizar ao salvar a solicita��o de viagem.";
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
				retorno.Mensagem = "A solicita��o de viagem a ser removida n�o foi encontrada.";
			}			
			
		} catch (Exception e) 
		{
			retorno.Mensagem = "Falha ao realizar a remov��o de solicita��o de viagem.";
		}
		
		return retorno;
	}
	
}
