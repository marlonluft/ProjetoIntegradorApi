package com.projetoIntegrador.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.projetoIntegrador.Conexao.Conexao;
import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Exceptions.EErrosBD;


public class Funcoes {
	
	public static int getId(String tabela) throws BDException{
		Connection conexao = Conexao.getConexao();
		try {
			PreparedStatement pst = conexao.prepareStatement("SELECT MAX(ID) ID FROM " + tabela + ";");
			ResultSet rs = pst.executeQuery();
			if (rs.first()) {
				return rs.getInt("ID");
			}
			return 0;
 		} catch (Exception e) {
 			throw new BDException(EErrosBD.CONSULTA, e.getMessage());
 		} finally {
 			Conexao.closeConexao();
 		}
	}
	
	public static java.sql.Date getDateSQL(Date data) {
	    return new java.sql.Date(data.getTime());
	}
	
	
	

}
