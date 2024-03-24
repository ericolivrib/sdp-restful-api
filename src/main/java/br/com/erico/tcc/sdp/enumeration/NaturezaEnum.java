package br.com.erico.tcc.sdp.enumeration;

public enum NaturezaEnum {
    SERVICO_TERCEIROS(1, "Serviço de Terceiros"),
    PASSAGENS(2, "Passagens"),
    MATERIAL_CONSUMO(3, "Material de Consumo"),
    MATERIAL_PERMANENTE(4, "Material Permanente"),
    DIARIAS(5, "Diárias"),
    BOLSAS(6, "Bolsas");

    private final Integer id;
    private final String nome;

    NaturezaEnum(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
