package com.desafio.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	DADOS_INVALIDOS("/dados_invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA("/erro_de_sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("/parametro_invalido", "Parâmetro inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-imcompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrado", "Recurso não encontrado"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://desafioStarWars.com.br" + path;
		this.title = title;
	}

}
