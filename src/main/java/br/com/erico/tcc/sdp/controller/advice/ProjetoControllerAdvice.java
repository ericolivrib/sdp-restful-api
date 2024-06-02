package br.com.erico.tcc.sdp.controller.advice;

import br.com.erico.tcc.sdp.dto.response.exception.ProjetoExistenteExceptionResponse;
import br.com.erico.tcc.sdp.dto.response.exception.ProjetoNaoEncontradoExceptionResponse;
import br.com.erico.tcc.sdp.dto.response.exception.StatusRemocaoProjetoInvalidoExceptionResponse;
import br.com.erico.tcc.sdp.exception.ProjetoExistenteException;
import br.com.erico.tcc.sdp.exception.ProjetoNaoEncontradoException;
import br.com.erico.tcc.sdp.exception.StatusRemocaoProjetoInvalidoException;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjetoControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProjetoControllerAdvice.class);

    @Hidden
    @ExceptionHandler(ProjetoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ProjetoNaoEncontradoExceptionResponse> handleProjetoNaoEncontradoException(ProjetoNaoEncontradoException e) {
        LOGGER.error(e.getMessage());
        var errorResponse = new ProjetoNaoEncontradoExceptionResponse(e.getMessage(), e.getProjetoId());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @Hidden
    @ExceptionHandler(ProjetoExistenteException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ResponseEntity<ProjetoExistenteExceptionResponse> handleProjetoExistenteException(ProjetoExistenteException e) {
        LOGGER.error(e.getMessage());
        var errorResponse = new ProjetoExistenteExceptionResponse(e.getMessage(), e.getNumeroProjeto());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @Hidden
    @ExceptionHandler(StatusRemocaoProjetoInvalidoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final ResponseEntity<StatusRemocaoProjetoInvalidoExceptionResponse> handleProjetoExistenteException(StatusRemocaoProjetoInvalidoException e) {
        LOGGER.error(e.getMessage());
        var errorResponse = new StatusRemocaoProjetoInvalidoExceptionResponse(e.getMessage(), e.getStatusEnum());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

}
