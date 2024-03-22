package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.NaturezaEnum;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "demanda")
public class Demanda {

    @Id
    @Column(name = "id", nullable = false, length = 20)
    private String id;
    @Column(name = "demanda", length = 100)
    private String nome;
    @Column(name = "id_natureza")
    private NaturezaEnum natureza;
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

    public NaturezaEnum getNatureza() {
        return natureza;
    }

    public void setNatureza(NaturezaEnum natureza) {
        this.natureza = natureza;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

}
