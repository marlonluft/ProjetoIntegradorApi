package com.projetoIntegrador.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projetoIntegrador.Conexao.Conexao;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Exceptions.EErrosBD;
import com.projetoIntegrador.Model.SetorModel;
import com.projetoIntegrador.Util.Funcoes;

public class SetorDAL {
	
	public static Integer Inserir(SetorModel model) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO SETOR (NOME, idusuario)"
                                                            +"VALUES (?, ?);");
			
			pst.setString(1, model.getNome());
			pst.setInt(2, model.getIdUsuario());
			pst.executeUpdate();
			return Funcoes.getId("SETOR");
		} catch (Exception e) {
			throw new BDException(EErrosBD.INSERE_DADO, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}

	public static SetorModel Buscar(Integer Id) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT * FROM SETOR WHERE ID = ?;");
			pst.setInt(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				return new SetorModel(
						rs.getInt("ID"), 
						rs.getString("NOME"), 
						rs.getInt("idusuario"));
			}
			return null;
 		} catch (Exception e) {
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} finally {
 			Conexao.closeConexao();
 		}
	}

	public static Boolean Alterar(SetorModel model) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("UPDATE SETOR SET NOME = ?, idusuario = ? WHERE ID = ?;");
			pst.setString(1, model.getNome());
			pst.setInt(2, model.getIdUsuario());
			pst.setInt(3, model.getId());
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
			PreparedStatement pst = conexao.prepareStatement("DELETE FROM SETOR WHERE ID = ?;");
			pst.setInt(1, Id);
			return pst.executeUpdate() > 0;
		} catch (Exception e) {
			throw new BDException(EErrosBD.EXCLUI, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	

	
	public static List<SetorModel> Listar() throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			List<SetorModel> pessoas = new ArrayList<SetorModel>();
			Statement st = conexao.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM SETOR;");
			while (rs.next()) {
				pessoas.add(new SetorModel(
						rs.getInt("ID"), 
						rs.getString("NOME"), 
						rs.getInt("idusuario")));
			}
			return pessoas;
		} catch (Exception e) {
			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}  
	
	public static String GetNome(Integer Id) throws BDException
	{
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT nome FROM SETOR WHERE ID = ?;");
			pst.setInt(1, Id);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) 
			{
				return rs.getString("nome");
			}
			
			return "Indisponível";
 		} catch (Exception e) 
		{
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} 
		finally 
		{
 			Conexao.closeConexao();
 		}
	}

	public static boolean Existe(Integer codSetor) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT nome FROM SETOR WHERE ID = ?;");
			pst.setInt(1, codSetor);
			ResultSet rs = pst.executeQuery();
			if (rs.first()) 
			{
				return true;
			}		
			
 		} catch (Exception e) 
		{
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} 
		finally 
		{
 			Conexao.closeConexao();
 		}
		
		return false;
	}

}

