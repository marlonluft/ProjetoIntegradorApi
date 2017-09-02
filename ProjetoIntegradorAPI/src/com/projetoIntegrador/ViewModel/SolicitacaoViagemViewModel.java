package com.projetoIntegrador.ViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.Model.SolicitacaoViagemModel;

@XmlRootElement
public class SolicitacaoViagemViewModel extends Retorno {

	public SolicitacaoViagemViewModel ()
	{
		
	}
	
	public SolicitacaoViagemViewModel (List<SolicitacaoViagemModel> lista)
	{
		this.Lista = new ArrayList<SolicitacaoViagemViewModel>();
		
		for (int i = 0; i < lista.size(); i++) 
		{
			this.Lista.add(new SolicitacaoViagemViewModel(lista.get(i)));
		}
	}
	
	public SolicitacaoViagemViewModel(SolicitacaoViagemModel model) {
		this.CidadeDestino = model.getCidadeDestino();
		this.CidadeOrigem = model.getCidadeOrigem();
		this.DataIda = model.getDataIda();
		this.DataVolta = model.getDataVolta();
		this.Id = model.getId();
		this.IdUsuario = model.getIdUsuario();
		this.Motivo = model.getMotivo();
		this.Observacao = model.getObservacao();
		this.Status = model.getStatus();
		this.UfDestino = model.getUfDestino();
		this.UfOrigem = model.getUfOrigem();		
		
		this.Custos = new ArrayList<SolicitacaoCustoViewModel>();
		
		for (int i = 0; i < model.getCustos().size(); i++) 
		{
			this.Custos.add(new SolicitacaoCustoViewModel(model.getCustos().get(i)));
		}
	}

	@XmlElement public int Id;
	@XmlElement public int IdUsuario;
	@XmlElement public String CidadeOrigem;
	@XmlElement public String UfOrigem;
	@XmlElement public String CidadeDestino;
	@XmlElement public String UfDestino;
	@XmlElement public Date DataIda;
	@XmlElement public Date DataVolta;
	@XmlElement public String Motivo;
	@XmlElement public String Observacao;
	@XmlElement public EStatus Status;
	@XmlElement public ArrayList<SolicitacaoCustoViewModel> Custos;
	
	@XmlElement List<SolicitacaoViagemViewModel> Lista;
	
}
