package br.com.erico.tcc.sdp.enumeration;

public enum StatusEnum {
    NAO_FINALIZADO(0, "Não Finalizado"),
    NAO_AVALIADO(1, "Não Avaliado"),
    APROVADO(2, "Aprovado"),
    AVALIADO_SUJEITO_A_AJUSTES(3, "Avaliado sujeito a ajuste"),
    NAO_APROVADO(4, "Não Aprovado"),
    MELHORAR_DESCRICAO(5, "Melhorar Descrição"),
    CADASTRADO(6, "Cadastrado"),
    EM_AVALIACAO(7, "Em Avaliação");

    private final Integer id;
    private final String nome;

    StatusEnum(Integer id, String nome) {
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
