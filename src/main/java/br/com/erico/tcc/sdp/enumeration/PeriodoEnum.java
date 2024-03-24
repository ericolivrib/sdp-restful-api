package br.com.erico.tcc.sdp.enumeration;

public enum PeriodoEnum {
    SUBMISSAO_PROJETOS(1, "Submissão de Projetos"),
    ANALISE_PROJETOS(2, "Análise de Projetos"),
    AJUSTE_PROJETOS(3, "Ajuste de Projetos"),
    ANALISE_DESCRICAO(4, "Ajuste de melhora de descrição"),
    ANALISE_AJUSTES(5, "Análise de Ajustes");

    private final Integer id;
    private final String nome;

    PeriodoEnum(Integer id, String nome) {
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
