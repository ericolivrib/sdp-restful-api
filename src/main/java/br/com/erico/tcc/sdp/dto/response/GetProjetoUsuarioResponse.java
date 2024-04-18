package br.com.erico.tcc.sdp.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record GetProjetoUsuarioResponse(UUID id, String numero, String nome, String modalidade, LocalDate dataCriacao, Short ano, Integer statusId, boolean finalizado, LocalDate dataFinalizacao) {
}
