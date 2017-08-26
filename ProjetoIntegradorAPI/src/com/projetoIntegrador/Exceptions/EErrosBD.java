package com.projetoIntegrador.Exceptions;

public enum EErrosBD {
	ABRE_CONEXAO ("Falha ao conectar com o banco de dados."),
	VERIFICA_EXISTENCIA_BASE_DADOS("Erro ao verificar se a base de dados existe."),
	VERIFICA_EXISTENCIA_TABELAS_NA_BASE("Erro ao verificar se as tabelas e views existem na base de dados."),
	CRIACAO_BASE_DADOS("Erro ao criar a base de dados."),
	DESTRUICAO_BASE_DADOS("Erro ao remover a base de dados."),
	FECHA_CONEXAO ("Falha ao fechar a conexão com o banco de dados"),
	CRIA_TABELA ("Erro ao criar a tabela no banco de dados."),
	EXCLUI_TABELA ("Erro ao excluir tabela do banco de dados."),
	INSERE_DADO ("Erro ao inserir dados na tabela."),
	ROLLBACK ("Erro ao fazer rollback no banco."),
	ATUALIZA ("Erro ao atualizar dados na tabela."),
	EXCLUI ("Erro ao excluuir registro na tabela."),
	CONSULTA ("Erro ao consultar dados na tabela."),
	CRIA_FORINGKEY ("Erro ao criar foringkeys das tabelas."),
	EXCLUI_FORINGKEY ("Erro ao excluir foringkeys das tabelas.");
	
	private final String descricaoErro;

	public String getDescricaoErro() {
		return descricaoErro;
	}
	
	private EErrosBD(String descricaoErro) {
		this.descricaoErro = descricaoErro;
	}
}