package br.com.erico.tcc.sdp.dto.response.exception;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record UsuarioNaoEncontradoExceptionResponse(

        @Schema(description = "Mensagem de erro", example = "Usuário não encontrado")
        String errorMessage,

        @Schema(description = "UUID do usuário não encontrado", example = "59a4aba2-ea91-4b1f-b12c-feb75ae10458")
        UUID usuarioId
) {
}
