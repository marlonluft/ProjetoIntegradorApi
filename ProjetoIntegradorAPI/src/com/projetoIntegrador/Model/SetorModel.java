package com.projetoIntegrador.Model;

public class SetorModel {
	
	public SetorModel(){}
	
	private Integer id;
	private String nome;
	private Integer codGestor;
	
	public SetorModel(Integer id, String nome, Integer codGestor) {
		this.id = id;
		this.nome = nome;
		this.codGestor = codGestor;
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
	public Integer getCodGestor() {
		return codGestor;
	}
	public void setCodGestor(Integer codGestor) {
		this.codGestor = codGestor;
	}
	@Override
	public String toString() {
		return "SetorModel [id=" + id + ", nome=" + nome + ", codGestor=" + codGestor + "]";
	}
}
