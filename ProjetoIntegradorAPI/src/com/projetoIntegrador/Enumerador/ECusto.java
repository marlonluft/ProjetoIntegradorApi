package com.projetoIntegrador.Enumerador;

public enum ECusto {

	PASSAGEM(0),
	HOSPEDAGEM(1),
	ALIMENTACAO(2),
	TRANSPORTE(3),
	OUTROS(4);
	
	private final int index;
	private ECusto(int valor){
		this.index = valor;
	}
	
	public int getIndex(){
		return index;
	}
	
	public static ECusto getEnum(int value) throws Exception{
        for (ECusto e:ECusto.values()) {
            if(e.getIndex() == value)
                return e;
        }
        
        throw new Exception("Falha ao recuperar Status.");
    }	
}
