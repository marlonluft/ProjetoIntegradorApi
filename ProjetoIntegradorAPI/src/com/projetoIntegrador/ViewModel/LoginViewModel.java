package com.projetoIntegrador.ViewModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Enumerador.EPerfil;

@XmlRootElement
public class LoginViewModel extends Retorno {
	@XmlElement public String Email;
	@XmlElement public String Senha;
	@XmlElement public Integer Id;
	@XmlElement public EPerfil Perfil;
}
