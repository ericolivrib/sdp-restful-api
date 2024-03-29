package br.com.erico.tcc.sdp.dto;

import java.util.UUID;

public record AdicionarProjetoDto(String numero, String nome, String modalidade, String justificativa, UUID usuarioId, Integer eixoTecnologicoId, String impactosAmbientais) {
}
