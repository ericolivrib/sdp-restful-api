package br.com.erico.tcc.sdp.dto.response.exception;

import br.com.erico.tcc.sdp.enumeration.StatusEnum;

public record StatusRemocaoProjetoInvalidoExceptionResponse(String errorMessage, StatusEnum status) {
}
