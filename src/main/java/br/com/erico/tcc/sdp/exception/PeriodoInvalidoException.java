package br.com.erico.tcc.sdp.exception;

import br.com.erico.tcc.sdp.enumeration.PeriodoEnum;

import java.time.LocalDate;

public class PeriodoInvalidoException extends RuntimeException {

    private final PeriodoEnum periodoEnum;
    private final LocalDate dataInicial;
    private final LocalDate dataFinal;

    public PeriodoInvalidoException(String message, PeriodoEnum periodoEnum, LocalDate dataInicial, LocalDate dataFinal) {
        super(message);
        this.periodoEnum = periodoEnum;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public PeriodoEnum getPeriodoEnum() {
        return periodoEnum;
    }

}
