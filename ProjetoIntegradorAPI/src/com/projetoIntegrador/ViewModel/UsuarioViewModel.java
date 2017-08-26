package com.projetoIntegrador.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Enumerador.ECargo;
import com.projetoIntegrador.Model.UsuarioModel;

@XmlRootElement
public class UsuarioViewModel extends Retorno {
	
	public UsuarioViewModel ()
	{
		
	}
	
	public UsuarioViewModel (UsuarioModel model)
	{
		this.Cargo = model.getCargo();
		this.Email = model.getEmail();
		this.Id = model.getId();
		this.IdSetor = model.getIdSetor();
		this.Nome = model.getNome();
		this.Senha = model.getSenha();	
		
		//this.Setor = SetorDAL.GetNome(this.IdSetor);
		this.PodeRemover = true;
		
		if (this.Cargo == ECargo.ADMINISTRADOR) {
			// verificar se é o único se for não pode excluir
			this.PodeRemover = false;
		}
		else if (this.Cargo == ECargo.GESTOR)
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
	@XmlElement public ECargo Cargo;
	@XmlElement public String Email;
	@XmlElement public String Senha;
	@XmlElement public Boolean PodeRemover;
	
	@XmlElement List<UsuarioViewModel> Lista;
}
