package br.com.erico.tcc.sdp.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "itens")
public class Item {

    @Id
    @Column(name = "uuid_item", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "unidade_medida", length = 30)
    private String unidadeMedida;
    @Column(name = "quantidade")
    private Double quantidade;
    @Column(name = "valor_unitario", precision = 12, scale = 2)
    private BigDecimal valorUnitario;
    @Column(name = "periodo")
    @ElementCollection
    private List<String> periodo;
    @Column(name = "justificativa")
    private String justificativa;
    @Column(name = "solicitacao")
    private String solicitacao;
    @ManyToOne
    @JoinColumn(name = "id_status", nullable = false)
    private Status status;
    @ManyToOne
    @JoinColumn(name = "id_demanda", nullable = false)
    private Demanda demanda;
    @ManyToOne
    @JoinColumn(name = "uuid_projeto", nullable = false)
    private Projeto projeto;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ParecerTecnico> pareceresTecnicos;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public List<String> getPeriodo() {
        return periodo;
    }

    public void setPeriodo(List<String> periodo) {
        this.periodo = periodo;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(String solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Demanda getDemanda() {
        return demanda;
    }

    public void setDemanda(Demanda demanda) {
        this.demanda = demanda;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public List<ParecerTecnico> getPareceresTecnicos() {
        return pareceresTecnicos;
    }

    public void setPareceresTecnicos(List<ParecerTecnico> pareceresTecnicos) {
        this.pareceresTecnicos = pareceresTecnicos;
    }

}
