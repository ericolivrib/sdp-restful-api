package br.com.erico.tcc.sdp.dto.response.exception;

import io.swagger.v3.oas.annotations.media.Schema;

public record ProjetoExistenteExceptionResponse(
        @Schema(description = "Mensagem de erro", example = "Já existem projetos cadastrados com este número")
        String errorMessage,

        @Schema(description = "Número do projeto já cadastrado", example = "123456")
        String numeroProjeto
) {
}
