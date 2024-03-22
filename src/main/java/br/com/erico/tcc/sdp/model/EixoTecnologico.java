package br.com.erico.tcc.sdp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "eixo_tecnologias")
public class EixoTecnologico {

    @Id
    @Column(name = "id_eixo", nullable = false)
    private Integer id;
    @Column(name = "codigo_eixo", length = 5)
    private String codigo;
    @Column(name = "eixo")
    private String nome;
    @OneToMany(mappedBy = "eixoTecnologico")
    private List<Projeto> projetos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

}
