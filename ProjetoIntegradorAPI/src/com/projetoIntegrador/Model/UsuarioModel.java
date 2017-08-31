package com.projetoIntegrador.Model;

import com.projetoIntegrador.Enumerador.EPerfil;
import com.projetoIntegrador.ViewModel.UsuarioViewModel;

public class UsuarioModel {

	public UsuarioModel ()
	{
		
	}
	
	public UsuarioModel(UsuarioViewModel model)
	{
		this.setPerfil(model.Perfil);
		this.setEmail(model.Email);
		this.setId(model.Id == null ? -1 : model.Id);
		this.setCodSetor(model.IdSetor == null ? -1 : model.IdSetor);
		this.setNome(model.Nome);
		this.setSenha(model.Senha);	
		this.setCpf(model.CPF);
	}

	public UsuarioModel(Integer id, String nome, String email, String senha, EPerfil perfil, Integer codSetor, String cpf) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.perfil = perfil;
		this.codSetor = codSetor;
		this.cpf = cpf;
	}
	
	private Integer id;
	private Integer codSetor;
	private String nome;
	private EPerfil perfil;
	private String email;
	private String senha;
	private String cpf;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	public EPerfil getPerfil() {
		return perfil;
	}
	public void setPerfil(EPerfil perfil) {
		this.perfil = perfil;
	}
	public Integer getCodSetor() {
		return codSetor;
	}
	public void setCodSetor(Integer codSetor) {
		this.codSetor = codSetor;
	}
	
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "UsuarioModel [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", perfil="
				+ perfil + ", codSetor=" + codSetor + "]";
	}
			
}
