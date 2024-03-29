package br.com.erico.tcc.sdp.dto;

import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import br.com.erico.tcc.sdp.model.Projeto;

import java.time.LocalDate;
import java.util.UUID;

public record ProjetoAdicionadoResponseDto(UUID id, String numero, String nome, String modalidade, String justificativa, UUID usuarioId, Integer eixoTecnologicoId, String impactosAmbientais,
                                           LocalDate dataCricao, Short ano, StatusEnum status) {
    public ProjetoAdicionadoResponseDto(Projeto p) {
        this(p.getId(), p.getNumero(), p.getNome(), p.getModalidade(), p.getJustificativa(), p.getUsuario().getId(),
                p.getEixoTecnologico().getId(), p.getImpactosAmbientais(), p.getDataCriacao(), p.getAno(),
                StatusEnum.NAO_FINALIZADO);
    }

}
