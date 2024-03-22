package br.com.erico.tcc.sdp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuario_departamento")
public class UsuarioDepartamento {

    @EmbeddedId
    private UsuarioDepartamentoId id;
    @ManyToOne
    @MapsId("usuarioId")
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @MapsId("departamentoId")
    @JoinColumn(name = "id_departamento")
    private Departamento departamento;
    @Column(name = "data_atribuicao")
    private LocalDate dataAtribuicao;

    public UsuarioDepartamentoId getId() {
        return id;
    }

    public void setId(UsuarioDepartamentoId id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public LocalDate getDataAtribuicao() {
        return dataAtribuicao;
    }

    public void setDataAtribuicao(LocalDate dataAtribuicao) {
        this.dataAtribuicao = dataAtribuicao;
    }
}