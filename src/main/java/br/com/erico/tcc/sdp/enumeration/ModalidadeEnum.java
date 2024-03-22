package br.com.erico.tcc.sdp.enumeration;

public enum ModalidadeEnum {

    ENSINO("Ensino"), PESQUISA("Pesquisa"), EXTENSAO("Extensão");

    private final String nome;

    ModalidadeEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
