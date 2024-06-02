package br.com.erico.tcc.sdp.controller.advice;

import br.com.erico.tcc.sdp.dto.response.exception.PeriodoInvalidoExceptionResponse;
import br.com.erico.tcc.sdp.exception.PeriodoInvalidoException;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PeriodoControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoControllerAdvice.class);

    @Hidden
    @ExceptionHandler(PeriodoInvalidoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<PeriodoInvalidoExceptionResponse> handlePeriodoInvalidoException(PeriodoInvalidoException e) {
        LOGGER.error(e.getMessage());
        var errorResponse = new PeriodoInvalidoExceptionResponse(e.getMessage(), e.getPeriodoEnum(), e.getDataInicial(), e.getDataFinal());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

}
