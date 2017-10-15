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
	
	
	
	public static Integer Inserir(SolicitacaoCustoModel model, Connection conexao) throws BDException {
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

	public static Boolean Alterar(SolicitacaoCustoModel model, Connection conexao) throws BDException {
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
		}
	}

	public static Boolean Deleter(Integer Id, Connection conexao) throws BDException {
		
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM CUSTOS WHERE ID = ?;");
			pst.setInt(1, Id);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
		}
	}
	
	public static Boolean DeleterPorSolicitacao(Integer Id, Connection conexaoObj) throws BDException {
		Connection conexao = conexaoObj == null ? Conexao.getConexao() : conexaoObj;
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM CUSTOS WHERE idsolicitacao = ?;");
			pst.setInt(1, Id);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
		} finally {
			if (conexaoObj == null)
			{
				Conexao.closeConexao();
			}
		}
	}
	
	public static Boolean DeleterPorusuario(Integer Id, Connection conexao) throws BDException {
		try {
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM custos where idsolicitacao IN (select id from solicitacao WHERE idusuario = ?);");
			pst.setInt(1, Id);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
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
	
	public static List<SolicitacaoCustoModel> ListarPorSolicitacao(int idSolicitacao, Connection conexao) throws BDException {
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
                      	 rs.getFloat("valor_prest")));
			}
			return pessoas;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		}
	}

	public static List<Integer> ListarIds(int idSolicitacao) throws BDException {
		try {
			Connection conexao = Conexao.getConexao();
			List<Integer> pessoas = new ArrayList<Integer>();

			PreparedStatement pst = conexao.prepareStatement("SELECT Id FROM CUSTOS WHERE idsolicitacao = ?;");
			pst.setInt(1, idSolicitacao);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pessoas.add(rs.getInt("ID"));
			}
			
			return pessoas;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		
	} finally {
		Conexao.closeConexao();
	}
	}  
	
	
}
