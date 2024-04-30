package br.com.erico.tcc.sdp.exception;

public class ProjetoExistenteException extends RuntimeException {

    private final String numeroProjeto;

    public ProjetoExistenteException(String errorMessage, String numeroProjeto) {
        super(errorMessage);
        this.numeroProjeto = numeroProjeto;
    }

    public String getNumeroProjeto() {
        return numeroProjeto;
    }

}
