package com.stagproj.BankingAPI.dtos.reponses;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class TransacaoResponse {

    private double valor;

    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate dataTransacao;

    private Long idConta;
}
