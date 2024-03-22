package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    @Column(name = "id_status", nullable = false)
    private Integer id;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public StatusEnum getNome() {
        return nome;
    }

    public void setNome(StatusEnum nome) {
        this.nome = nome;
    }

}