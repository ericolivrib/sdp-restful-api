package br.com.erico.tcc.sdp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "demanda")
public class Demanda {

    @Id
    @Column(name = "id_demanda", nullable = false, length = 20)
    private String id;
    @Column(name = "demanda", length = 100)
    private String nome;
    @ManyToOne
    @JoinColumn(name = "id_natureza", nullable = false)
    private Natureza natureza;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "exemplos")
    private String exemplo;
    @OneToMany(mappedBy = "demanda", cascade = CascadeType.ALL)
    private List<Item> itens;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Natureza getNatureza() {
        return natureza;
    }

    public void setNatureza(Natureza natureza) {
        this.natureza = natureza;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExemplo() {
        return exemplo;
    }

    public void setExemplo(String exemplo) {
        this.exemplo = exemplo;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

}
