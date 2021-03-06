package com.projetoIntegrador.Controller;

import java.sql.Connection;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.Conexao.Conexao;
import com.projetoIntegrador.DAL.AcessoDAL;
import com.projetoIntegrador.DAL.SolicitacaoCustoDAL;
import com.projetoIntegrador.DAL.SolicitacaoViagemDAL;
import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Enumerador.EPerfil;
import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.AcessoModel;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;
import com.projetoIntegrador.Model.UsuarioModel;
import com.projetoIntegrador.ViewModel.ExcluirSolicitacaoViewModel;
import com.projetoIntegrador.ViewModel.Retorno;
import com.projetoIntegrador.ViewModel.SolicitacaoViagemViewModel;

@Path("/solicitacao")
public class SolicitacaoController {

	@POST
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public SolicitacaoViagemViewModel Listar(AcessoModel acesso) {
		SolicitacaoViagemViewModel retorno = new SolicitacaoViagemViewModel();

		try {
			if (AcessoDAL.AcessoValido(acesso.IdAcesso, acesso.UsuarioId)) {				
				List<SolicitacaoViagemModel> lista = null;

				UsuarioModel usuario = UsuarioDAL.Buscar(acesso.UsuarioId);
				if (usuario == null) {
					throw new Exception("Usu�rio logado n�o encontrado.");
				} else if (usuario.getPerfil() == EPerfil.GESTOR) {
					lista = SolicitacaoViagemDAL.Listar(acesso.UsuarioId, true);
				} else {
					lista = SolicitacaoViagemDAL.Listar(acesso.UsuarioId, false);
				}

				retorno = new SolicitacaoViagemViewModel(lista);
				retorno.AcessoValido = true;
				retorno.Sucesso = true;
			}

		} catch (Exception e) {
			retorno = new SolicitacaoViagemViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de solicita��es de viagem.";
		}

		return retorno;
	}

	@POST
	@Path("/salvar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Salvar(SolicitacaoViagemViewModel solicitacao) {
		Retorno retorno = new Retorno();

		try {
			if (AcessoDAL.AcessoValido(solicitacao.IdAcesso, solicitacao.UsuarioId)) {
				retorno.AcessoValido = true;
				SolicitacaoViagemModel model = new SolicitacaoViagemModel(solicitacao);

				if (model.getStatus() == EStatus.EM_ABERTO
						&& model.getDataIda().getTime() > model.getDataVolta().getTime()) {
					retorno.Mensagem = "Data de Ida � maior que a Data de Volta.";
				} else {
					if (model.getId() >= 0) {
						if (solicitacao.EnviarAprovacao) {
							model.setStatus(EStatus.AGUARDANDO_APROVACAO_VIAGEM);
						} else if (solicitacao.Aprovado) {
							model.setStatus(EStatus.EM_ABERTO_CONTAS);
						} else if (solicitacao.Reprovado) {
							model.setStatus(EStatus.RECUSADO_VIAGEM);
						} else if (solicitacao.EnviarAprovacaoCustos) {
							model.setStatus(EStatus.AGUARDANDO_APROVACAO_CONTAS);

							for (int i = 0; i < model.getCustos().size(); i++) {
								Float valor = model.getCustos().get(i).getValorPrestado();
								model.getCustos().get(i).setValorPrestado(valor == null ? 0 : valor);
							}
						} else if (solicitacao.AprovadoCustos) {
							model.setStatus(EStatus.FINALIZADO);
						} else if (solicitacao.ReprovadoCustos) {
							model.setStatus(EStatus.RECUSADO_CONTAS);
						}

						VerificarCustosRemovidos(model);
						SolicitacaoViagemDAL.Alterar(model);
					} else {
						if (solicitacao.EnviarAprovacao) {
							model.setStatus(EStatus.AGUARDANDO_APROVACAO_VIAGEM);
						} else {
							model.setStatus(EStatus.EM_ABERTO);
						}

						VerificarCustosRemovidos(model);
						SolicitacaoViagemDAL.Inserir(model);
					}

					retorno.Sucesso = true;
				}
			}
		} catch (Exception e) {
			retorno.Mensagem = "Falha ao realizar ao salvar a solicita��o de viagem.";
		}

		return retorno;
	}

	private void VerificarCustosRemovidos(SolicitacaoViagemModel model) throws BDException {
		try {
			Connection conexao = Conexao.getConexao();

			List<Integer> custosIds = SolicitacaoCustoDAL.ListarIds(model.getId());

			// Loop ids custos salvos no banco de dados
			for (int i = 0; i < custosIds.size(); i++) {
				Boolean encontrou = false;

				// Loop custo vindos da tela
				for (int j = 0; j < model.getCustos().size(); j++) {
					if (custosIds.get(i) == model.getCustos().get(j).getId()) {
						encontrou = true;
					}
				}

				if (!encontrou) {
					// Se n�o encontrar o id na listagem, remove do banco
					SolicitacaoCustoDAL.Deleter(custosIds.get(i), conexao);
				}
			}
		} finally {
			Conexao.closeConexao();
		}
	}

	@POST
	@Path("/remover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Remover(ExcluirSolicitacaoViewModel model) {
		Retorno retorno = new Retorno();

		try {
			if (AcessoDAL.AcessoValido(model.IdAcesso, model.UsuarioId)) {
				retorno.AcessoValido = true;
				if (SolicitacaoViagemDAL.Deleter(Integer.parseInt(model.id))) {
					retorno.Sucesso = true;
				} else {
					retorno.Mensagem = "A solicita��o de viagem a ser removida n�o foi encontrada.";
				}
			}
		} catch (Exception e) {
			retorno.Mensagem = "Falha ao realizar a remov��o de solicita��o de viagem.";
		}

		return retorno;
	}

}
