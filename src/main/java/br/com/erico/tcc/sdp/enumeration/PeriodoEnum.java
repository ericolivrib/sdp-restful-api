package br.com.erico.tcc.sdp.enumeration;

public enum PeriodoEnum {
    SUBMISSAO_PROJETOS("Submissão de Projetos"),
    ANALISE_PROJETOS("Análise de Projetos"),
    AJUSTE_PROJETOS("Ajuste de Projetos"),
    ANALISE_AJUSTES("Análise de Ajustes"),
    ANALISE_DESCRICAO("Ajuste de melhora de descrição");

    private final String nome;

    PeriodoEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
