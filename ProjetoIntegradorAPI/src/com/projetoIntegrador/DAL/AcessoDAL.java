package com.projetoIntegrador.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.projetoIntegrador.Conexao.Conexao;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Exceptions.EErrosBD;
import com.projetoIntegrador.Util.Funcoes;

public class AcessoDAL {

	public static Integer Inserir(int idUsuario) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("INSERT INTO ACESSO (idusuario)"
                                                            +"VALUES (?);");
			
			pst.setInt(1, idUsuario);
			pst.executeUpdate();
			
			return Funcoes.getId("ACESSO");
		} catch (Exception e) {
			throw new BDException(EErrosBD.INSERE_DADO, e.getMessage());
		} finally {
			Conexao.closeConexao();
		}
	}
	
	public static Boolean AcessoValido(int idAcesso, int idUsuario ) throws BDException {
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT max(id) AS 'id' FROM ACESSO WHERE idusuario = ?;");
			pst.setInt(1, idUsuario);
			
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				return rs.getInt("id") == idAcesso;
			}
			return false;
 		} catch (Exception e) {
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} finally {
 			Conexao.closeConexao();
 		}
	}
	
}
