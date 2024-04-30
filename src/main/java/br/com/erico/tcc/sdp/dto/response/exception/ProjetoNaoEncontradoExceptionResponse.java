package br.com.erico.tcc.sdp.dto.response.exception;

import java.util.UUID;

public record ProjetoNaoEncontradoExceptionResponse(String errorMessage, UUID projetoId) {
}
