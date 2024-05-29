package br.com.erico.tcc.sdp.dto.request;

import java.util.UUID;

public record CreateProjetoRequest(
        String numero,
        String nome,
        String modalidade,
        String justificativa,
        UUID usuarioId,
        Integer eixoTecnologicoId,
        String impactosAmbientais
) {
}
