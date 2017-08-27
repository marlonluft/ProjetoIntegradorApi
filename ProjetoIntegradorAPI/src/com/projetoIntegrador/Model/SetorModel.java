package com.projetoIntegrador.Model;

public class SetorModel {
	
	public SetorModel(){}
	
	private Integer id;
	private String nome;
	private Integer idusuario;
	
	public SetorModel(Integer id, String nome, Integer idusuario) {
		this.id = id;
		this.nome = nome;
		this.idusuario = idusuario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdUsuario() {
		return idusuario;
	}
	public void setIdUsuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	@Override
	public String toString() {
		return "SetorModel [id=" + id + ", nome=" + nome + ", Id usuário(gestor)=" + idusuario + "]";
	}
}
