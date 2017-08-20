package com.projetoIntegrador.ViewModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Retorno {
	
	public Retorno()
	{
		Sucesso = false;
		Mensagem = "";
	}
	
	@XmlElement public boolean Sucesso;
	@XmlElement public String Mensagem;		
	
}
