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
	
	public static UsuarioModel VerificarLogin(String email, String senha) 
	{
		
		UsuarioModel user = new UsuarioModel();
		
		switch (senha) {
		case "admin":
			user.setCargo(ECargo.ADMINISTRADOR);
			break;
			
		case "gestor":
			user.setCargo(ECargo.GESTOR);
			break;
			
		default:
		case "colab":
			user.setCargo(ECargo.COLABORADOR);
			break;
		}
		
		user.setId(123);
		
		return email.equals("admin@admin") && (senha.equals("admin") || senha.equals("gestor") || senha.equals("colab")) ? user : null;
	}

}
