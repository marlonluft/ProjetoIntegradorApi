package com.projetoIntegrador.Model;

import java.util.ArrayList;
import java.util.Date;

import com.projetoIntegrador.Enumerador.EStatus;

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
	private ArrayList<SolicitacaoCustoModel> custos;
	
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
		return custos;
	}
	public void setCustos(ArrayList<SolicitacaoCustoModel> custos) {
		this.custos = custos;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}	
	
}
