package br.com.erico.tcc.sdp.controller.advice;

import br.com.erico.tcc.sdp.dto.response.exception.UsuarioNaoEncontradoExceptionResponse;
import br.com.erico.tcc.sdp.exception.UsuarioNaoEncontradoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsuarioControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioControllerAdvice.class);

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<UsuarioNaoEncontradoExceptionResponse> handleProjetoNaoEncontradoException(final UsuarioNaoEncontradoException e) {
        LOGGER.error(e.getMessage());
        var errorResponse = new UsuarioNaoEncontradoExceptionResponse(e.getMessage(), e.getUsuarioId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

}
