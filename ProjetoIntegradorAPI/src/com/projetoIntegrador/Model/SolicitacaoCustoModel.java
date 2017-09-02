package com.projetoIntegrador.Model;

import com.projetoIntegrador.Enumerador.ECusto;

public class SolicitacaoCustoModel {

	private int id;
	private int idSolicitacao;
	private ECusto tipo;
	private int quantidade;
	private float valorSolicitado;
	private Float valorPrestado;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(int idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public ECusto getTipo() {
		return tipo;
	}
	public void setTipo(ECusto tipo) {
		this.tipo = tipo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public float getValorSolicitado() {
		return valorSolicitado;
	}
	public void setValorSolicitado(float valorSolicitado) {
		this.valorSolicitado = valorSolicitado;
	}
	public Float getValorPrestado() {
		return valorPrestado;
	}
	public void setValorPrestado(Float valorPrestado) {
		this.valorPrestado = valorPrestado;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
}