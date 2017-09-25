package com.projetoIntegrador.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.projetoIntegrador.Conexao.Conexao;
import com.projetoIntegrador.Enumerador.ECusto;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Exceptions.EErrosBD;
import com.projetoIntegrador.Model.SolicitacaoCustoModel;
import com.projetoIntegrador.Util.Funcoes;

public class SolicitacaoCustoDAL {
	
	
	
	public static Integer Inserir(SolicitacaoCustoModel model) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO CUSTOS (idsolicitacao, tipo, quantidade, valor_solic, valor_prest)"
                                                            +"VALUES (?, ?, ?, ?, ?);");
			pst.setInt(1, model.getIdSolicitacao());
			pst.setInt(2, model.getTipo().getIndex());
			pst.setInt(3, model.getQuantidade());
			pst.setFloat(4, model.getValorSolicitado());
			pst.setObject(5, model.getValorPrestado(), Types.INTEGER);
			pst.executeUpdate();
			return Funcoes.getId("CUSTOS");
		} catch (Exception e) {
			throw new BDException(EErrosBD.INSERE_DADO, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}

	public static SolicitacaoCustoModel Buscar(Integer Id) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM CUSTOS WHERE ID = ?;");
			pst.setInt(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				return new SolicitacaoCustoModel(rs.getInt("ID"), 
					                           	 rs.getInt("idsolicitacao"), 
					                           	 ECusto.getEnum(rs.getInt("TIPO")), 
					                           	 rs.getInt("quantidade"), 
					                           	 rs.getFloat("valor_solic"),
					                           	 rs.getFloat("valor_prest"));
			}
			return null;
 		} catch (Exception e) {
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} finally {
 			Conexao.closeConexao();
 		}
	}

	public static Boolean Alterar(SolicitacaoCustoModel model) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE CUSTOS SET idsolicitacao = ?, tipo = ?, quantidade = ?, valor_solic = ?, valor_prest = ? WHERE ID = ?;");
			pst.setInt(1, model.getIdSolicitacao());
			pst.setInt(2, model.getTipo().getIndex());
			pst.setInt(3, model.getQuantidade());
			pst.setFloat(4, model.getValorSolicitado());
			pst.setObject(5, model.getValorPrestado(), Types.INTEGER);
			pst.setInt(6, model.getId());
	
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.ATUALIZA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}

	public static Boolean Deleter(Integer Id) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM CUSTOS WHERE ID = ?;");
			pst.setInt(1, Id);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	
	public static Boolean DeleterPorSolicitacao(Integer Id) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM CUSTOS WHERE idsolicitacao = ?;");
			pst.setInt(1, Id);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	

	
	public static List<SolicitacaoCustoModel> Listar() throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			List<SolicitacaoCustoModel> pessoas = new ArrayList<SolicitacaoCustoModel>();
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM CUSTOS;");
			while (rs.next()) {
				pessoas.add(new SolicitacaoCustoModel(rs.getInt("ID"), 
                      	 rs.getInt("idsolicitacao"), 
                      	 ECusto.getEnum(rs.getInt("TIPO")), 
                      	 rs.getInt("quantidade"), 
                      	 rs.getFloat("valor_solic"),
                      	 rs.getFloat("valor_prest")));
			}
			return pessoas;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}  
	
	public static List<SolicitacaoCustoModel> ListarPorSolicitacao(int idSolicitacao) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			List<SolicitacaoCustoModel> pessoas = new ArrayList<SolicitacaoCustoModel>();

			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM CUSTOS WHERE idsolicitacao = ?;");
			pst.setInt(1, idSolicitacao);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pessoas.add(new SolicitacaoCustoModel(rs.getInt("ID"), 
                      	 rs.getInt("idsolicitacao"), 
                      	 ECusto.getEnum(rs.getInt("TIPO")), 
                      	 rs.getInt("quantidade"), 
                      	 rs.getFloat("valor_solic"),
                      	 rs.getFloat("valor_solic")));
			}
			return pessoas;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}  
	
	
}
