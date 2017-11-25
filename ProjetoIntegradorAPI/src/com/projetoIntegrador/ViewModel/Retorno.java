package com.projetoIntegrador.ViewModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Model.AcessoModel;

@XmlRootElement
public class Retorno extends AcessoModel {
	
	public Retorno()
	{
		Sucesso = false;
		Mensagem = "";
		AcessoValido = false;
	}
	
	@XmlElement public boolean Sucesso;
	@XmlElement public String Mensagem;
	@XmlElement public Boolean AcessoValido;	
	
}
