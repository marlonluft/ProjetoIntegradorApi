package com.projetoIntegrador.ViewModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Enumerador.ECargo;

@XmlRootElement
public class LoginViewModel extends Retorno {
	@XmlElement public String Email;
	@XmlElement public String Senha;
	@XmlElement public Integer Id;
	@XmlElement public ECargo Cargo;
}
