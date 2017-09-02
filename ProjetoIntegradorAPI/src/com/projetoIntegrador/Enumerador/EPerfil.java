package com.projetoIntegrador.Enumerador;

public enum EPerfil {
	Null(0),
	ADMINISTRADOR(1),
	COLABORADOR(2),
	GESTOR(3);
	
	private final int index;
	private EPerfil(int valor){
		this.index = valor;
	}
	
	public int getIndex(){
		return index;
	}
	
	public static EPerfil getEnum(int value){
        for (EPerfil e:EPerfil.values()) {
            if(e.getIndex() == value)
                return e;
        }
        return EPerfil.Null;
    }	
}