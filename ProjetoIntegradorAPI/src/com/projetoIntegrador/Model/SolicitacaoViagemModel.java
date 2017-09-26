package com.projetoIntegrador.Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.projetoIntegrador.Enumerador.EStatus;
import com.projetoIntegrador.ViewModel.SolicitacaoViagemViewModel;

public class SolicitacaoViagemModel {

	private int id;
	private int idUsuario;
	private String cidadeOrigem;
	private String ufOrigem;
	private String cidadeDestino;
	private String ufDestino;
	private Date dataIda;
	private Date dataVolta;
	private String motivo;
	private String observacao;
	private EStatus status;
	private String justificativa;
	private ArrayList<SolicitacaoCustoModel> custos;
	
	public SolicitacaoViagemModel(int ID, int cod_usuario, String cidade_origem, String uf_origem, String cidade_destino, String uf_destino,
			Date data_Ida, Date data_volta, String motivo, String observacao, EStatus status, String justificativa,
			ArrayList<SolicitacaoCustoModel> listaCustos) {
		
		this.setCidadeDestino(cidade_destino);
		this.setCidadeOrigem(cidade_origem);
		this.setCustos(listaCustos);
		this.setDataIda(data_Ida);
		this.setDataVolta(data_volta);
		this.setId(ID);
		this.setIdUsuario(cod_usuario);
		this.setMotivo(motivo);
		this.setObservacao(observacao);
		this.setStatus(status);
		this.setUfDestino(uf_destino);
		this.setUfOrigem(uf_origem);
		this.setJustificativa(justificativa);
		
	}
	
	public SolicitacaoViagemModel(SolicitacaoViagemViewModel model) throws Exception
	{
		this.setCidadeDestino(model.CidadeDestino);
		this.setCidadeOrigem(model.CidadeOrigem);
		this.setId(model.Id);
		this.setIdUsuario(model.IdUsuario);
		this.setJustificativa(model.Justificativa);
		this.setMotivo(model.Motivo);
		this.setObservacao(model.Observacao);
		this.setStatus(EStatus.getEnum(model.Status));
		this.setUfDestino(model.UfDestino);
		this.setUfOrigem(model.UfOrigem);
		this.setJustificativa(model.Justificativa);
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		
		this.setDataIda(new java.sql.Date(format.parse(model.DataIdaS).getTime()));
		this.setDataVolta(new java.sql.Date(format.parse(model.DataVoltaS).getTime()));
		
		this.custos = new ArrayList<SolicitacaoCustoModel>();
		
		for (int i = 0; i < model.Custos.size(); i++) 
		{
			this.custos.add(new SolicitacaoCustoModel(model.Custos.get(i)));
		}
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getCidadeOrigem() {
		return cidadeOrigem;
	}
	public void setCidadeOrigem(String cidadeOrigem) {
		this.cidadeOrigem = cidadeOrigem;
	}
	public String getUfOrigem() {
		return ufOrigem;
	}
	public void setUfOrigem(String ufOrigem) {
		this.ufOrigem = ufOrigem;
	}
	public String getCidadeDestino() {
		return cidadeDestino;
	}
	public void setCidadeDestino(String cidadeDestino) {
		this.cidadeDestino = cidadeDestino;
	}
	public String getUfDestino() {
		return ufDestino;
	}
	public void setUfDestino(String ufDestino) {
		this.ufDestino = ufDestino;
	}
	public Date getDataIda() {
		return dataIda;
	}
	public void setDataIda(Date dataIda) {
		this.dataIda = dataIda;
	}
	public Date getDataVolta() {
		return dataVolta;
	}
	public void setDataVolta(Date dataVolta) {
		this.dataVolta = dataVolta;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacaop) {
		observacao = observacaop;
	}
	public EStatus getStatus() {
		return status;
	}
	public void setStatus(EStatus status) {
		this.status = status;
	}
	public ArrayList<SolicitacaoCustoModel> getCustos() {
		return custos == null? new ArrayList<SolicitacaoCustoModel>() : custos;
	}
	public void setCustos(ArrayList<SolicitacaoCustoModel> custos) {
		this.custos = custos;
	}
	
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
}
