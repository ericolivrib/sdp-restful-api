package br.com.erico.tcc.sdp.exception;

import java.util.UUID;

public class ProjetoNaoEncontradoException extends RuntimeException {

    private final UUID projetoId;

    public ProjetoNaoEncontradoException(String errorMessage, UUID projetoId) {
        super(errorMessage);
        this.projetoId = projetoId;
    }

    public UUID getProjetoId() {
        return projetoId;
    }

}
