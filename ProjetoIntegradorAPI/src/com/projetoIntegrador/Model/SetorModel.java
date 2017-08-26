package com.projetoIntegrador.Model;

public class SetorModel {

	public SetorModel()
	{
		
	}
	
	private Integer id;
	private Integer idGestor;
	private String nomeSetor;
		
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdGestor() {
		return idGestor;
	}

	public void setIdGestor(Integer idGestor) {
		this.idGestor = idGestor;
	}

	public String getNomeSetor() {
		return nomeSetor;
	}

	public void setNomeSetor(String nomeSetor) {
		this.nomeSetor = nomeSetor;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}
