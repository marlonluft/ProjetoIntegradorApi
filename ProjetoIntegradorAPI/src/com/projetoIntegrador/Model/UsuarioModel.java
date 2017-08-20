package com.projetoIntegrador.Model;

import java.util.Date;

import com.projetoIntegrador.Enumerador.ECargo;

public class UsuarioModel {

	public UsuarioModel()
	{
		
	}
	
	private String nome;
	private Date dataAdmissao;
	private ECargo cargo;
	private String usuario;
	private String senha;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataAdmissao(Date dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public ECargo getCargo() {
		return cargo;
	}
	public void setCargo(ECargo cargo) {
		this.cargo = cargo;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}	
}
