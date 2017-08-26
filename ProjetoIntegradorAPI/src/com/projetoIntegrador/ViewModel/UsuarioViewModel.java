package com.projetoIntegrador.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
		
		//this.Setor = SetorDAL.GetNome(this.IdSetor);
		this.PodeRemover = true;
		
		if (this.Perfil == EPerfil.ADMINISTRADOR) {
			// verificar se é o único se for não pode excluir
			this.PodeRemover = false;
		}
		else if (this.Perfil == EPerfil.GESTOR)
		{
			// verificar se não contém setores, se não conter pode excluir
			this.PodeRemover = false;
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
