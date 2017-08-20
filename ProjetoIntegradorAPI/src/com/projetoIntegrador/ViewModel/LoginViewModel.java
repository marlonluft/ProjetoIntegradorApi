package com.projetoIntegrador.ViewModel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LoginViewModel {
	@XmlElement public String Email;
	@XmlElement public String Senha;
}
