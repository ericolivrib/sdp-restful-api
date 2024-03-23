package br.com.erico.tcc.sdp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class UsuarioDepartamentoId implements Serializable {

    @Column(name = "uuid_usuario", nullable = false)
    private UUID usuarioId;
    @Column(name = "id_departamento", nullable = false)
    private Integer departamentoId;

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Integer getDepartamentoId() {
        return departamentoId;
    }

    public void setDepartamentoId(Integer departamentoId) {
        this.departamentoId = departamentoId;
    }

}
