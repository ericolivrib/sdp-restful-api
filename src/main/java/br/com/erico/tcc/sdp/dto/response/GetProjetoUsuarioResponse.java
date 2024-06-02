package br.com.erico.tcc.sdp.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

public record GetProjetoUsuarioResponse(
        @Schema(description = "UUID do projeto")
        UUID id,

        @Schema(description = "Número do projeto igual ao do Portal de Projetos da UFSM", example = "123456")
        String numero,

        @Schema(description = "Nome do projeto", example = "Transição de Arquitetura Monolítica para RESTful")
        String nome,

        @Schema(description = "Modalidade do projeto", example = "Ensino")
        String modalidade,

        @Schema(description = "Data de criação do projeto", example = "2024-05-30")
        LocalDate dataCriacao,

        @Schema(description = "Ano de criação do projeto", example = "2024")
        Short ano,

        @Schema(description = "ID numérico do status do projeto", example = "0")
        Integer statusId,

        @Schema(description = "Indica se o projeto já está finalizado", example = "false")
        boolean finalizado,

        @Schema(description = "Data de finalização da solicitação de demandas do projeto", example = "2024-05-30")
        LocalDate dataFinalizacao
) {
}
