package com.projetoIntegrador.ViewModel;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Enumerador.ECusto;
import com.projetoIntegrador.Model.SolicitacaoCustoModel;

@XmlRootElement
public class SolicitacaoCustoViewModel extends Retorno {

	public SolicitacaoCustoViewModel()
	{
		
	}
	
	public SolicitacaoCustoViewModel(SolicitacaoCustoModel model) {
		this.Id = model.getId();
		this.IdSolicitacao = model.getIdSolicitacao();
		this.Quantidade = model.getQuantidade();
		this.Tipo = model.getTipo();
		this.ValorPrestado = model.getValorPrestado();
		this.ValorSolicitado = model.getValorSolicitado();
	}

	@XmlElement public int Id;
	@XmlElement public int IdSolicitacao;
	@XmlElement public ECusto Tipo;
	@XmlElement public int Quantidade;
	@XmlElement public float ValorSolicitado;
	@XmlElement public Float ValorPrestado;
	
	@XmlElement List<SolicitacaoCustoViewModel> Lista;
}
