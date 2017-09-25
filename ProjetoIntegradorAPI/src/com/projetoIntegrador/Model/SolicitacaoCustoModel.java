package com.projetoIntegrador.Model;

import com.projetoIntegrador.Enumerador.ECusto;
import com.projetoIntegrador.ViewModel.SolicitacaoCustoViewModel;

public class SolicitacaoCustoModel {

	private int id;
	private int idSolicitacao;
	private ECusto tipo;
	private int quantidade;
	private float valorSolicitado;
	private Float valorPrestado;
	
	public SolicitacaoCustoModel(int ID, int COD_SOLIC, ECusto TIPO, int quantidade, float valor_solic, float valor_prest) {
		this.setId(ID);
		this.setIdSolicitacao(COD_SOLIC);
		this.setQuantidade(quantidade);
		this.setTipo(TIPO);
		this.setValorPrestado(valor_prest);
		this.setValorSolicitado(valor_solic);
	}
	public SolicitacaoCustoModel(SolicitacaoCustoViewModel model) throws Exception {
		this.setId(model.Id);
		this.setIdSolicitacao(model.IdSolicitacao);
		this.setQuantidade(model.Quantidade);
		this.setTipo(ECusto.getEnum(model.TipoI));
		this.setValorPrestado(model.ValorPrestado);
		this.setValorSolicitado(model.ValorSolicitado);
	}
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