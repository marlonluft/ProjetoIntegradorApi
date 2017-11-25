package com.projetoIntegrador.Model;

import javax.xml.bind.annotation.XmlElement;

public class AcessoModel {
	
	public AcessoModel()
	{
		
	}
	
	@XmlElement public int IdAcesso;
	@XmlElement public int UsuarioId;
}
