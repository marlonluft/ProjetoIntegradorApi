package com.projetoIntegrador.DAL;

import com.projetoIntegrador.Model.UsuarioModel;

public class UsuarioDAL {

	public static Integer Inserir(UsuarioModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	public static UsuarioModel Buscar(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean Alterar(UsuarioModel model) {
		// TODO Auto-generated method stub
		return null;
	}

	public static Boolean Deleter(Integer Id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static Boolean VerificarLogin(String usuario, String senha) {
		
		return usuario.equals("admin") && senha.equals("admin");
	}

}
