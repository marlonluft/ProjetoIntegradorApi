package com.projetoIntegrador.Model;

import com.projetoIntegrador.Enumerador.ECargo;
import com.projetoIntegrador.ViewModel.UsuarioViewModel;

public class UsuarioModel {

	public UsuarioModel ()
	{
		
	}
	
	public UsuarioModel(UsuarioViewModel model)
	{
		this.setCargo(model.Cargo);
		this.setEmail(model.Email);
		this.setId(model.Id == null ? 0 : model.Id);
		this.setIdSetor(model.IdSetor == null ? 0 : model.IdSetor);
		this.setNome(model.Nome);
		this.setSenha(model.Senha);		
	}
	
	private Integer id;
	private Integer idSetor;
	private String nome;
	private ECargo cargo;
	private String email;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public ECargo getCargo() {
		return cargo;
	}
	public void setCargo(ECargo cargo) {
		this.cargo = cargo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	
	public Integer getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(Integer idSetor) {
		this.idSetor = idSetor;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}	
}
