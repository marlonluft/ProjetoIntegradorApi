package com.projetoIntegrador.DAL;

import com.projetoIntegrador.Enumerador.ECargo;
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
	
	public static UsuarioModel VerificarLogin(String email, String senha) {
		
		UsuarioModel user = new UsuarioModel();
		user.setCargo(ECargo.COLABORADOR);
		user.setId(123);
		
		return email.equals("admin@admin") && senha.equals("admin") ? user : null;
	}

}
