package com.projetoIntegrador.Exceptions;

public class BDException extends Exception {

	private static final long serialVersionUID = 1L;

	public BDException(EErrosBD erro, String msg) {
		super(erro.getDescricaoErro() + "#" + msg);
	}
}