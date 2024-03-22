package br.com.erico.tcc.sdp.model;

import br.com.erico.tcc.sdp.enumeration.StatusParecerTecnicoEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parecer_tecnico")
public class ParecerTecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;
    @Column(name = "local")
    private String local;
    @Column(name = "parecer_tecnico")
    private String descricao;
    @Column(name = "status_parecer")
    @Enumerated(EnumType.STRING)
    private StatusParecerTecnicoEnum status;
    @Column(name = "recurso")
    private String recurso;
    @Column(name = "data_parecer")
    private LocalDate dataParecer;
    @Column(name = "quantidade_item")
    private Double quantidadeAjustada;
    @Column(name = "valor_item", precision = 12, scale = 2)
    private BigDecimal valorAjustado;
    @Column(name = "periodo_item")
    @ElementCollection
    private List<String> periodoAjustado;
    @ManyToOne
    @JoinColumn(name = "id_item", nullable = false)
    private Item item;
    @ManyToOne
    @JoinColumn(name = "id_responsavel", nullable = false)
    private Usuario responsavel;
    @ManyToOne
    @JoinColumn(name = "id_departamento", nullable = false)
    private Departamento departamento;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public StatusParecerTecnicoEnum getStatus() {
        return status;
    }

    public void setStatus(StatusParecerTecnicoEnum status) {
        this.status = status;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public LocalDate getDataParecer() {
        return dataParecer;
    }

    public void setDataParecer(LocalDate dataParecer) {
        this.dataParecer = dataParecer;
    }

    public Double getQuantidadeAjustada() {
        return quantidadeAjustada;
    }

    public void setQuantidadeAjustada(Double quantidadeAjustada) {
        this.quantidadeAjustada = quantidadeAjustada;
    }

    public BigDecimal getValorAjustado() {
        return valorAjustado;
    }

    public void setValorAjustado(BigDecimal valorAjustado) {
        this.valorAjustado = valorAjustado;
    }

    public List<String> getPeriodoAjustado() {
        return periodoAjustado;
    }

    public void setPeriodoAjustado(List<String> periodoAjustado) {
        this.periodoAjustado = periodoAjustado;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
