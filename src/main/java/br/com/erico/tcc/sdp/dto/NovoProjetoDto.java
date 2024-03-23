package br.com.erico.tcc.sdp.dto;

import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import br.com.erico.tcc.sdp.model.EixoTecnologico;
import br.com.erico.tcc.sdp.model.Projeto;
import br.com.erico.tcc.sdp.model.Status;
import br.com.erico.tcc.sdp.model.Usuario;

import java.time.LocalDate;
import java.util.UUID;

public record NovoProjetoDto(String numero, String nome, String modalidade, String justificativa, UUID usuarioId, Integer eixoTecnologicoId, String impactosAmbientais) {

    public Projeto toProjetoEntity() {
        var projeto = new Projeto();
        projeto.setNumero(this.numero());
        projeto.setNome(this.nome());
        projeto.setModalidade(this.modalidade());
        projeto.setJustificativa(this.justificativa());

        var usuario = new Usuario();
        usuario.setId(usuarioId);
        projeto.setUsuario(usuario);

        var eixoTecnologico = new EixoTecnologico();
        eixoTecnologico.setId(this.eixoTecnologicoId());
        projeto.setEixoTecnologico(eixoTecnologico);

        projeto.setImpactosAmbientais(this.impactosAmbientais());
        projeto.setDataCriacao(LocalDate.now());
        projeto.setAno((short) LocalDate.now().getYear());

        var status = new Status();
        status.setId(StatusEnum.NAO_FINALIZADO.ordinal());
        projeto.setStatus(status);
        
        return projeto;
    }

}
