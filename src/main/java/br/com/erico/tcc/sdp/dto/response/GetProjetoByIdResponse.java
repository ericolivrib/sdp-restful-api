package br.com.erico.tcc.sdp.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record GetProjetoByIdResponse(UUID id, String numero, String nome, String modalidade, String justificativa, LocalDate dataCriacao, Short ano, Integer statusId, boolean portalProjetos, LocalDate dataFinalizacao, String impactosAmbientais) {
}
