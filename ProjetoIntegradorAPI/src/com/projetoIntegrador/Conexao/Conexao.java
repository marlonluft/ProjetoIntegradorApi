package com.projetoIntegrador.Conexao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.projetoIntegrador.Exceptions.BDException;
import com.projetoIntegrador.Exceptions.EErrosBD;

public class Conexao {

	private static Connection conn = null;
	//private final static String SERVIDOR_BD = "projetointegradorbd.mysql.database.azure.com";
	private final static String SERVIDOR_BD = "localhost";
	private final static int PORTA_BD = 3306;
	private final static String DATABASE = "projetointegradordb";
	private final static String USUARIO_BD = "projeto@projetointegradorbd";
	private final static String SENHA_BD = "Integrador12";
	 	
	public static Connection getConexao() throws BDException 
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			conn = DriverManager.getConnection("jdbc:mysql://" + SERVIDOR_BD
					+ ":" + PORTA_BD + "/" + DATABASE,  USUARIO_BD, SENHA_BD);
			
			return conn;

		} catch (Exception e) {

			throw new BDException(EErrosBD.ABRE_CONEXAO,
					e.getMessage());
		}

	}

	public static void closeConexao() throws BDException {
		try {
			if (conn instanceof Connection) {
				conn.close();
				conn = null;
			}
		} catch (Exception e) {
			throw new BDException(EErrosBD.FECHA_CONEXAO,
					e.getMessage());
		}
	}

	public static String getDatabase() {
		return DATABASE;
	}
}