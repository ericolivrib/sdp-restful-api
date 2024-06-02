package br.com.erico.tcc.sdp.dto.response.exception;

import br.com.erico.tcc.sdp.enumeration.PeriodoEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record PeriodoInvalidoExceptionResponse(
        @Schema(description = "Mensagem de erro", example = "Período de submissão de projetos não está vigente")
        String errorMessage,

        @Schema(description = "Nome do período", example = "Submissão de Projetos")
        PeriodoEnum periodo,

        @Schema(description = "Data do inicío do período", example = "2024-05-31")
        LocalDate dataInicial,

        @Schema(description = "Data do fim do período", example = "2024-05-31")
        LocalDate dataFinal
) {
}
