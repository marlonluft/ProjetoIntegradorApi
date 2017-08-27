package com.projetoIntegrador.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.DAL.UsuarioDAL;
import com.projetoIntegrador.Model.SetorModel;


@XmlRootElement
public class SetorViewModel extends Retorno {

	public SetorViewModel() {
		
	}
	
	public SetorViewModel(List<SetorModel> lista) {
		this.Lista = new ArrayList<SetorViewModel>();
		
		for (int i = 0; i < lista.size(); i++) 
		{
			this.Lista.add(new SetorViewModel(lista.get(i)));
		}
	}

	public SetorViewModel(SetorModel model) {
		this.Nome = model.getNome();
		this.Id = model.getId();
		this.IdGestor = model.getIdUsuario();
		
		try
		{
			this.Colaboradores = UsuarioDAL.GetQuantidadeColaboradores(this.Id);
		}
		catch (Exception e) {
			this.Colaboradores = 0;
		}
		
		try
		{
			this.Gestor = UsuarioDAL.GetNome(this.IdGestor);
		}
		catch (Exception e) {
			this.Gestor = "Indisponível";
		}
		
		try 
		{
			this.PodeRemover = UsuarioDAL.GetQuantidadeColaboradores(this.Id) == 0;
		}
		catch (Exception e) {
			this.PodeRemover = false;
		}
	}

	@XmlElement public Integer Id;
	@XmlElement public Integer IdGestor;
	@XmlElement public String Nome;
	@XmlElement public String Gestor;
	@XmlElement public Integer Colaboradores;
	@XmlElement public Boolean PodeRemover;
	
	@XmlElement public List<SetorViewModel> Lista;
	
}