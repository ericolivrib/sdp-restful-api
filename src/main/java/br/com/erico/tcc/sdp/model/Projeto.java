package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.ModalidadeEnum;
import br.com.erico.tcc.sdp.enumeration.StatusEnum;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projeto")
public class Projeto {

    @Id
    @Column(name = "uuiid_projeto", nullable = false)
    private UUID id;
    @Column(name = "numero_projeto", nullable = false, length = 6)
    private String numero;
    @Column(name = "nome_projeto")
    private String nome;
    @Column(name = "modalidade", length = 10)
    @Enumerated(EnumType.STRING)
    private ModalidadeEnum modalidade;
    @Column(name = "justificativa")
    private String justificativa;
    @Column(name = "impactos_ambientais")
    private String impactosAmbientais;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    @Column(name = "finalizado")
    private boolean finalizado;
    @Column(name = "data_finalizacao")
    private LocalDate dataFinalizacao;
    @Column(name = "ano", length = 4)
    private Short ano;
    @Column(name = "portal_projeto")
    private boolean portalProjetos;
    @Column(name = "id_status")
    @Enumerated(EnumType.ORDINAL)
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "uuid_usuario", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_eixo", nullable = false)
    private EixoTecnologico eixoTecnologico;
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Item> itens;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ModalidadeEnum getModalidade() {
        return modalidade;
    }

    public void setModalidade(ModalidadeEnum modalidade) {
        this.modalidade = modalidade;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getImpactosAmbientais() {
        return impactosAmbientais;
    }

    public void setImpactosAmbientais(String impactosAmbientais) {
        this.impactosAmbientais = impactosAmbientais;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDate getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(LocalDate dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }

    public Short getAno() {
        return ano;
    }

    public void setAno(Short ano) {
        this.ano = ano;
    }

    public boolean isPortalProjetos() {
        return portalProjetos;
    }

    public void setPortalProjetos(boolean portalProjetos) {
        this.portalProjetos = portalProjetos;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public EixoTecnologico getEixoTecnologico() {
        return eixoTecnologico;
    }

    public void setEixoTecnologico(EixoTecnologico eixoTecnologico) {
        this.eixoTecnologico = eixoTecnologico;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

}
