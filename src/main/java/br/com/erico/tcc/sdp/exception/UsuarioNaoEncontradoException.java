package br.com.erico.tcc.sdp.exception;

import java.util.UUID;

public class UsuarioNaoEncontradoException extends RuntimeException {

    private final UUID usuarioId;

    public UsuarioNaoEncontradoException(String message, UUID usuarioId) {
        super(message);
        this.usuarioId = usuarioId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

}
