package com.projetoIntegrador.Controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projetoIntegrador.DAL.AcessoDAL;
import com.projetoIntegrador.DAL.SetorDAL;
import com.projetoIntegrador.DAL.SolicitacaoViagemDAL;
import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Enumerador.EPerfil;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Model.AcessoModel;
import com.projetoIntegrador.Model.UsuarioModel;
import com.projetoIntegrador.ViewModel.LoginViewModel;
import com.projetoIntegrador.ViewModel.Retorno;
import com.projetoIntegrador.ViewModel.UsuarioViewModel;

@Path("/usuario")
public class UsuarioController {
	@POST
	@Path("/logar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginViewModel Logar(LoginViewModel model) {
		LoginViewModel retorno = new LoginViewModel();

		try {
			UsuarioModel usuario = UsuarioDAL.VerificarLogin(model.CPF, model.Senha);

			if (usuario != null) {
				retorno.Sucesso = true;
				retorno.Perfil = usuario.getPerfil();
				retorno.Id = usuario.getId();
				retorno.Nome = usuario.getNome();
				retorno.AcessoValido = true;
				retorno.AcessoId = AcessoDAL.Inserir(usuario.getId());
			} else {
				retorno.Mensagem = retorno.Sucesso ? "" : "Usu�rio e/ou senha inv�lido, tente novamente.";
			}
		} catch (Exception e) {
			retorno.Mensagem = "Erro ao realizar o logn, tente novamente. Erro: " + e.getMessage();
		}

		return retorno;
	}

	@POST
	@Path("/listar")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioViewModel Listar(AcessoModel acesso) {
		UsuarioViewModel retorno = new UsuarioViewModel();

		try {
			if (AcessoDAL.AcessoValido(acesso.IdAcesso, acesso.UsuarioId)) {
				retorno.AcessoValido = true;
				List<UsuarioModel> lista = UsuarioDAL.Listar();
				retorno = new UsuarioViewModel(lista);
				retorno.Sucesso = true;
			}
		} catch (BDException e) {
			retorno = new UsuarioViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de usu�rios.";
		}

		return retorno;
	}

	@POST
	@Path("/consultar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public UsuarioViewModel Consultar(UsuarioViewModel model) {
		UsuarioViewModel retorno = new UsuarioViewModel();

		try {
			if (AcessoDAL.AcessoValido(model.IdAcesso, model.UsuarioId)) {
				UsuarioModel usuario = UsuarioDAL.Buscar(model.Id);

				retorno = new UsuarioViewModel(usuario);
				retorno.AcessoValido = true;
				return retorno;
			}
		} catch (BDException e) {
			return new UsuarioViewModel();
		}

		return new UsuarioViewModel();
	}

	@POST
	@Path("/manipular")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Manipular(UsuarioViewModel model) {
		Retorno retorno = new Retorno();

		try {
			if (AcessoDAL.AcessoValido(model.IdAcesso, model.UsuarioId)) {
				retorno.AcessoValido = true;
				UsuarioModel usuarioModel = new UsuarioModel(model);

				if (usuarioModel.getPerfil() == EPerfil.COLABORADOR && !SetorDAL.Existe(usuarioModel.getCodSetor())) {
					retorno.Mensagem = "O setor selecionado n�o existe";
				} else {
					if (usuarioModel.getId() >= 0) {
						UsuarioModel usuario;

						try {
							usuario = UsuarioDAL.Buscar(model.Id);
						} catch (Exception e) {
							usuario = null;
						}

						if (usuario == null) {
							retorno.Mensagem = "Usu�rio n�o encontrado";
						} else if (usuario.getPerfil() == EPerfil.ADMINISTRADOR
								&& usuarioModel.getPerfil() != EPerfil.ADMINISTRADOR
								&& UsuarioDAL.GetQuantidadeAdministradores() == 1) {
							retorno.Mensagem = "N�o � poss�vel a alterar o perfil deste usu�rio, pois o mesmo � �nico administrador no sistema.";
						} else {
							if (!usuario.getEmail().equals(usuarioModel.getEmail())
									&& UsuarioDAL.Existe(usuarioModel.getEmail())) {
								retorno.Mensagem = "E-mail j� vinculado a outro usu�rio.";
							} else if (!usuario.getCpf().equals(usuarioModel.getCpf())
									&& UsuarioDAL.ExisteCPF(usuarioModel.getCpf())) {
								retorno.Mensagem = "CPF j� vinculado a outro usu�rio.";
							} else if (usuario.getPerfil() == EPerfil.GESTOR
									&& usuarioModel.getPerfil() != EPerfil.GESTOR
									&& SetorDAL.ContemGestor(usuarioModel.getId())) {
								retorno.Mensagem = "H� setores vinculados a este gestor, favor remover o vinculo para continuar.";
							} else {
								if (usuario.getPerfil() == EPerfil.COLABORADOR
										&& usuarioModel.getPerfil() != EPerfil.COLABORADOR) {
									// Remove as solicita��es criadas pelo usu�rio
									SolicitacaoViagemDAL.DeleterPorusuario(usuario.getId());
								}

								retorno.Sucesso = UsuarioDAL.Alterar(usuarioModel);
							}
						}
					} else {
						if (UsuarioDAL.Existe(usuarioModel.getEmail())) {
							retorno.Mensagem = "E-mail j� vinculado a outro usu�rio.";
						} else if (UsuarioDAL.ExisteCPF(usuarioModel.getCpf())) {
							retorno.Mensagem = "CPF j� vinculado a outro usu�rio.";
						} else {
							retorno.Sucesso = UsuarioDAL.Inserir(usuarioModel) >= 0;
						}
					}
				}

				if (!retorno.Sucesso && (retorno.Mensagem == null || retorno.Mensagem.length() == 0)) {
					retorno.Mensagem = "Houve um erro ao realiza a a��o, favor contactar o suporte.";
				}
			}
		} catch (Exception e) {
			retorno.Mensagem = "Houve um erro ao realiza a a��o: " + e.getMessage();
		}

		return retorno;
	}

	@POST
	@Path("/remover")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Retorno Remover(UsuarioViewModel model) {
		Retorno retorno = new Retorno();

		try {
			if (AcessoDAL.AcessoValido(model.IdAcesso, model.UsuarioId)) {
				retorno.AcessoValido = true;
				UsuarioModel usuario;

				try {
					usuario = UsuarioDAL.Buscar(model.Id);
				} catch (Exception e) {
					usuario = null;
				}

				if (usuario != null) {
					if (usuario.getPerfil() == EPerfil.GESTOR && SetorDAL.ContemGestor(usuario.getId())) {
						retorno.Mensagem = "Um ou mais setores est�o vinculados a este gestor, remova o vinculo na tela de setores para continuar.";
					} else if (usuario.getPerfil() == EPerfil.ADMINISTRADOR
							&& UsuarioDAL.GetQuantidadeAdministradores() == 1) {
						retorno.Mensagem = "Este administrado n�o pode ser removido pois ele � o �nico no sistema.";
					} else {
						// Remove as solicita��es criadas pelo usu�rio
						SolicitacaoViagemDAL.DeleterPorusuario(model.Id);

						// Remove o usu�rio.
						retorno.Sucesso = UsuarioDAL.Deleter(model.Id);
					}
				} else {
					retorno.Mensagem = "Usu�rio n�o encontrado";
				}

				if (!retorno.Sucesso && (retorno.Mensagem == null || retorno.Mensagem.length() == 0)) {
					retorno.Mensagem = "Houve um erro ao realiza a a��o, favor contactar o suporte.";
				}
			}
		} catch (Exception e) {
			retorno.Mensagem = "Houve um erro ao realiza a a��o: " + e.getMessage();
		}

		return retorno;
	}

	@POST
	@Path("/listarGestores")
	@Produces(MediaType.APPLICATION_JSON)
	public UsuarioViewModel ListarGestores(AcessoModel acesso) {
		UsuarioViewModel retorno = new UsuarioViewModel();

		try {
			if (AcessoDAL.AcessoValido(acesso.IdAcesso, acesso.UsuarioId)) {
				retorno.AcessoValido = true;
				List<UsuarioModel> lista = UsuarioDAL.Listar(true);
				retorno = new UsuarioViewModel(lista);
				retorno.Sucesso = true;
			}
		} catch (BDException e) {
			retorno = new UsuarioViewModel();
			retorno.Mensagem = "Falha ao realizar a listagem de usu�rios.";
		}

		return retorno;
	}
}