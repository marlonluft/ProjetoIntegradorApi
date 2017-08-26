package com.projetoIntegrador.ViewModel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SetorViewModel extends Retorno {

	@XmlElement public Integer Id;
	@XmlElement public Integer IdGestor;
	@XmlElement public String NomeSetor;
	
	@XmlElement public List<SetorViewModel> Lista;
	
}