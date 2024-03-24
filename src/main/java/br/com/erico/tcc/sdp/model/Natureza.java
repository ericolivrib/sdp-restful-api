package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.NaturezaEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "natureza")
public class Natureza {

    @Id
    @Column(name = "id_natureza", nullable = false)
    private Integer id;
    @Column(name = "natureza")
    private String nome;
    @OneToMany(mappedBy = "natureza")
    private List<Demanda> demandas;

    public Natureza() {
    }

    public Natureza(NaturezaEnum naturezaEnum) {
        this.id = naturezaEnum.getId();
        this.nome = naturezaEnum.getNome();
    }

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

    public List<Demanda> getDemandas() {
        return demandas;
    }

    public void setDemandas(List<Demanda> demandas) {
        this.demandas = demandas;
    }

}
