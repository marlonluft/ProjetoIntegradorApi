package com.projetoIntegrador.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.projetoIntegrador.Conexao.Conexao;
import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Exceptions.EErrosBD;
import com.projetoIntegrador.Model.SolicitacaoCustoModel;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;
import com.projetoIntegrador.Util.Funcoes;

public class SolicitacaoViagemDAL {
	
	
	
	public static Integer Inserir(SolicitacaoViagemModel model) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO SOLICITACAO (idusuario, cidade_origem, uf_origem, cidade_destino, uf_destino, data_ida, data_volta, motivo, observacao, status, justificativa)"
                                                            +"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			
			pst.setInt(1, model.getIdUsuario());
			pst.setString(2, model.getCidadeOrigem());
			pst.setString(3, model.getUfOrigem());
			pst.setString(4, model.getCidadeDestino());
			pst.setString(5, model.getUfDestino());
			pst.setDate(6, Funcoes.getDateSQL(model.getDataIda()));
			pst.setDate(7, Funcoes.getDateSQL(model.getDataVolta()));
			pst.setString(8, model.getMotivo());
			pst.setString(9, model.getObservacao());
			pst.setInt(10, model.getStatus().getIndex());
			pst.setString(11, model.getJustificativa());	
			pst.executeUpdate();
			
			for (int i = 0; i < model.getCustos().size(); i++) {
				SolicitacaoCustoDAL.Inserir(model.getCustos().get(i));
			}
			
			return Funcoes.getId("SOLICITACAO");
		} catch (Exception e) {
			throw new BDException(EErrosBD.INSERE_DADO, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}

	public static SolicitacaoViagemModel Buscar(Integer Id) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM SOLICITACAO WHERE ID = ?;");
			pst.setInt(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				return new SolicitacaoViagemModel(rs.getInt("ID"),
						                           rs.getInt("idusuario"),
                                                   rs.getString("cidade_origem"), 
                                                   rs.getString("uf_origem"), 
                                                   rs.getString("cidade_destino"),
                                                   rs.getString("uf_destino"), 
			                                       rs.getDate("data_Ida"), 
			                                       rs.getDate("data_volta"),
			                                       rs.getString("motivo"), 
			                                       rs.getString("observacao"), 
			                                       EStatus.getEnum(rs.getInt("status")),
			                                       rs.getString("justificativa"),
                                                   (ArrayList<SolicitacaoCustoModel>) SolicitacaoCustoDAL.ListarPorSolicitacao(rs.getInt("ID"))); 			
			}
			return null;
 		} catch (Exception e) {
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} finally {
 			Conexao.closeConexao();
 		}
	}

	public static Boolean Alterar(SolicitacaoViagemModel model) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE SOLICITACAO SET idusuario = ?, cidade_origem = ?, uf_origem = ?, cidade_destino = ?, uf_destino = ?, data_ida = ?, data_volta = ?, motivo = ?, observacao = ? , status = ?, justificativa = ? WHERE ID = ?;");
			pst.setInt(1, model.getIdUsuario());
			pst.setString(2, model.getCidadeOrigem());
			pst.setString(3, model.getUfOrigem());
			pst.setString(4, model.getCidadeDestino());
			pst.setString(5, model.getUfDestino());
			pst.setDate(6, Funcoes.getDateSQL(model.getDataIda()));
			pst.setDate(7, Funcoes.getDateSQL(model.getDataVolta()));
			pst.setString(8, model.getMotivo());
			pst.setString(9, model.getObservacao());
			pst.setInt(10, model.getStatus().getIndex());
			pst.setString(11, model.getJustificativa());
			pst.setInt(12, model.getId());
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.ATUALIZA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}

	public static Boolean Deleter(Integer Id) throws BDException {		
		try {			
				SolicitacaoCustoDAL.DeleterPorSolicitacao(Id);
				
				Connection conexao = Conexao.getConexao();
				PreparedStatement pst = conexao.prepareStatement("DELETE FROM SOLICITACAO WHERE ID = ?;");
				pst.setInt(1, Id);
				return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	

	
	public static List<SolicitacaoViagemModel> Listar(int idUsuario) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			List<SolicitacaoViagemModel> pessoas = new ArrayList<SolicitacaoViagemModel>();
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM SOLICITACAO WHERE idUsuario = ?;");
			pst.setInt(1, idUsuario);
			ResultSet rs = pst.executeQuery();			
			
			while (rs.next()) {
				pessoas.add(new SolicitacaoViagemModel(rs.getInt("ID"),
                        rs.getInt("idusuario"),
                        rs.getString("cidade_origem"), 
                        rs.getString("uf_origem"), 
                        rs.getString("cidade_destino"),
                        rs.getString("uf_destino"), 
                        rs.getDate("data_Ida"), 
                        rs.getDate("data_volta"),
                        rs.getString("motivo"), 
                        rs.getString("observacao"), 
                        EStatus.getEnum(rs.getInt("status")),
                        rs.getString("justificativa"),
                        (ArrayList<SolicitacaoCustoModel>) SolicitacaoCustoDAL.ListarPorSolicitacao(rs.getInt("ID"))));
			}
			return pessoas;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}  

}
