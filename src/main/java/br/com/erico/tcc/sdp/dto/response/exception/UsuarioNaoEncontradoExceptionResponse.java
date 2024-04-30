package br.com.erico.tcc.sdp.dto.response.exception;

import java.util.UUID;

public record UsuarioNaoEncontradoExceptionResponse(String errorMessage, UUID usuarioId) {
}
