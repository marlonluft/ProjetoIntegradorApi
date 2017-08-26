package com.projetoIntegrador.DAL;

import java.util.ArrayList;
import java.util.List;

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
	
	public static List<UsuarioModel> Listar() {
		
		List<UsuarioModel> lista = new ArrayList<UsuarioModel>();
		
		UsuarioModel user1 = new UsuarioModel();
		user1.setCargo(ECargo.ADMINISTRADOR);
		user1.setEmail("email@email.com");
		user1.setId(0);
		user1.setIdSetor(0);
		user1.setNome("Nome");
		user1.setSenha("senha");		
		lista.add(user1);
		
		UsuarioModel user2 = new UsuarioModel();
		user2.setCargo(ECargo.GESTOR);
		user2.setEmail("email@email.com");
		user2.setId(0);
		user2.setIdSetor(0);
		user2.setNome("Nome");
		user2.setSenha("senha");		
		lista.add(user2);
		
		UsuarioModel user3 = new UsuarioModel();
		user3.setCargo(ECargo.COLABORADOR);
		user3.setEmail("email@email.com");
		user3.setId(0);
		user3.setIdSetor(0);
		user3.setNome("Nome");
		user3.setSenha("senha");		
		lista.add(user3);
		 
		return lista;
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
