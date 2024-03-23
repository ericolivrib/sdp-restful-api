package br.com.erico.tcc.sdp.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ProjetoUsuarioResponseDto(UUID id, String numero, String nome, String modalidade, LocalDate dataCriacao, Short ano, Integer statusId, boolean finalizado, LocalDate dataFinalizacao) {
}
