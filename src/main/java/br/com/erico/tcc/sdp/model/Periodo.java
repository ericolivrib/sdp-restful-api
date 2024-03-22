package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.PeriodoEnum;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "periodo")
public class Periodo {

    @Id
    @Column(name = "id_periodo", nullable = false)
    private Integer id;
    @Column(name = "periodo")
    private String nome;
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

}