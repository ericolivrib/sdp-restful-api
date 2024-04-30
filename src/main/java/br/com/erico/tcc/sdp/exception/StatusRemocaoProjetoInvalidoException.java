package br.com.erico.tcc.sdp.exception;

import br.com.erico.tcc.sdp.enumeration.StatusEnum;

public class StatusRemocaoProjetoInvalidoException extends RuntimeException {

    private final StatusEnum statusEnum;

    public StatusRemocaoProjetoInvalidoException(String errorMessage, StatusEnum statusEnum) {
        super(errorMessage);
        this.statusEnum = statusEnum;
    }

    public StatusEnum getStatusEnum() {
        return statusEnum;
    }

}
