package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "status")
public class Status {

    @Id
    @Column(name = "id_status", nullable = false)
    private Integer id;
    @Column(name = "status")
    private String nome;
    @OneToMany(mappedBy = "status")
    private List<Projeto> projetos;
    @OneToMany(mappedBy = "status")
    private List<Item> itens;

    public Status() {
    }

    public Status(StatusEnum statusEnum) {
        this.id = statusEnum.getId();
        this.nome = statusEnum.getNome();
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

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

}