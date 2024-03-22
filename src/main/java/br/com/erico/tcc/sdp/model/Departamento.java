package br.com.erico.tcc.sdp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "departamento")
public class Departamento {
    @Id
    @Column(name = "id_departamento", nullable = false)
    private Integer id;
    @Column(name = "departamento", length = 30)
    private String nome;
    @OneToMany(mappedBy = "departamento")
    private List<ParecerTecnico> pareceresTecnicos;
    @OneToMany(mappedBy = "departamento")
    private List<UsuarioDepartamento> usuarios;

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

    public List<ParecerTecnico> getPareceresTecnicos() {
        return pareceresTecnicos;
    }

    public void setPareceresTecnicos(List<ParecerTecnico> pareceresTecnicos) {
        this.pareceresTecnicos = pareceresTecnicos;
    }

    public List<UsuarioDepartamento> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<UsuarioDepartamento> usuarios) {
        this.usuarios = usuarios;
    }

}