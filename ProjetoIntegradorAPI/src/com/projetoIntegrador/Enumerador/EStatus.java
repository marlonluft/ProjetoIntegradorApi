package com.projetoIntegrador.Enumerador;

public enum EStatus {
	
	EM_ABERTO(0),
	AGUARDANDO_APROVACAO_VIAGEM(1),
	RECUSADO_VIAGEM(2),
	EM_ABERTO_CONTAS(3),
	AGUARDANDO_APROVACAO_CONTAS(4),	
	RECUSADO_CONTAS(5),
	FINALIZADO(6);
	
	private final int index;
	private EStatus(int valor){
		this.index = valor;
	}
	
	public int getIndex(){
		return index;
	}
	
	public static EStatus getEnum(int value) throws Exception{
        for (EStatus e:EStatus.values()) {
            if(e.getIndex() == value)
                return e;
        }
        
        throw new Exception("Falha ao recuperar Status.");
    }	
}
