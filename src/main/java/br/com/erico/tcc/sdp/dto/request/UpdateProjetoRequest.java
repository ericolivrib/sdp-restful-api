package br.com.erico.tcc.sdp.dto.request;

public record UpdateProjetoRequest(String numero, String nome, String modalidade, String justificativa, String impactosAmbientais) {
}
