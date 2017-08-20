package com.projetoIntegrador.ViewModel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Model.UsuarioModel;

@XmlRootElement
public class UsuarioViewModel extends Retorno {

	@XmlElement List<UsuarioModel> Usuarios;
}
