package br.com.erico.tcc.sdp.dto;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateProjetoDto(String numero, String nome, String modalidade, String justificativa, String impactosAmbientais) {
}
