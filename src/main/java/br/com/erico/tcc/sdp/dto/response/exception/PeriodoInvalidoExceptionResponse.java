package br.com.erico.tcc.sdp.dto.response.exception;

import br.com.erico.tcc.sdp.enumeration.PeriodoEnum;

import java.time.LocalDate;

public record PeriodoInvalidoExceptionResponse(String errorMessage, PeriodoEnum periodo, LocalDate dataInicial, LocalDate dataFinal) {
}
