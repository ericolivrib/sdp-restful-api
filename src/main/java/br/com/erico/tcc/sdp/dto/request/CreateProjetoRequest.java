package br.com.erico.tcc.sdp.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CreateProjetoRequest(
        @Schema(
                description = "Número do projeto igual ao do Portal de Projetos da UFSM",
                example = "123456"
        )
        String numero,

        @Schema(description = "Nome do projeto", example = "Transição de Arquitetura Monolítica para RESTful")
        String nome,

        @Schema(description = "Modalidade do projeto", example = "Ensino")
        String modalidade,

        @Schema(description = "Justificativa para a criação do projeto", example = "Finalizar o curso de Sistemas para Internet")
        String justificativa,

        @Schema(description = "UUID do usuário proponente")
        UUID usuarioId,

        @Schema(description = "ID do eixo tecnológico do projeto", example = "4")
        Integer eixoTecnologicoId,

        @Schema(
                description = "Possíveis impactos ambientais que o projeto pode causar durante o seu desenvolvimento",
                example = "Impactos ambientais desconhecidos"
        )
        String impactosAmbientais
) {
}
