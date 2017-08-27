package com.projetoIntegrador.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.DAL.SetorDAL;
import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Enumerador.EPerfil;
import com.projetoIntegrador.Model.UsuarioModel;

@XmlRootElement
public class UsuarioViewModel extends Retorno {
	
	public UsuarioViewModel ()
	{
		
	}
	
	public UsuarioViewModel (UsuarioModel model)
	{
		this.Perfil = model.getPerfil();
		this.Email = model.getEmail();
		this.Id = model.getId();
		this.IdSetor = model.getCodSetor();
		this.Nome = model.getNome();
		this.Senha = model.getSenha();	
		
		try {
			this.Setor = SetorDAL.GetNome(this.IdSetor);
		}
		catch (Exception e) {
			this.Setor = "Indisponível";
		}
		
		this.PodeRemover = true;
		
		if (this.Perfil == EPerfil.ADMINISTRADOR) {
			// Verificar se é o único se for não pode excluir
			try {
				this.PodeRemover = UsuarioDAL.GetQuantidadeAdministradores() > 1;
			}
			catch (Exception e) {
				this.PodeRemover = false;
			}
		}
		else if (this.Perfil == EPerfil.GESTOR)
		{
			// Verificar se não contém setores, se não conter pode excluir
			this.PodeRemover = (this.Setor == null || this.Setor.length() == 0 || this.Setor.equals("Indisponível"));
		}
	}
	
	public UsuarioViewModel (List<UsuarioModel> lista)
	{
		this.Lista = new ArrayList<UsuarioViewModel>();
		
		for (int i = 0; i < lista.size(); i++) 
		{
			this.Lista.add(new UsuarioViewModel(lista.get(i)));
		}
	}
	
	@XmlElement public Integer Id;
	@XmlElement public Integer IdSetor;
	@XmlElement public String Setor;
	@XmlElement public String Nome;
	@XmlElement public EPerfil Perfil;
	@XmlElement public String Email;
	@XmlElement public String Senha;
	@XmlElement public Boolean PodeRemover;
	
	@XmlElement List<UsuarioViewModel> Lista;
}
