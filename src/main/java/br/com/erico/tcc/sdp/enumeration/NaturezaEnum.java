package br.com.erico.tcc.sdp.enumeration;

public enum NaturezaEnum {
    SERVICO_TERCEIROS("Serviço de Terceiros"),
    PASSAGENS("Passagens"),
    MATERIAL_CONSUMO("Material de Consumo"),
    MATERIAL_PERMANENTE("Material Permanente"),
    DIARIAS("Diárias"),
    BOLSAS("Bolsas");

    private final String nome;

    NaturezaEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
