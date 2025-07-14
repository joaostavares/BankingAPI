package com.studyproj.banking.domain.reponses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(name = "Transaction Response DTO", description = "Transaction Response DTO Model")
@Getter
@Setter

public class TransactionResponse {
    @Schema(description = "Transaction ID generated automatically", example = "1")
    private Long id;

    @Schema(description = "Transaction Type", example = "withdraw")
    private String transactionType;

    @Schema(description = "Transaction ID generated automatically", example = "1")
    private double amount;

    @Schema(description = "Balance Before Transaction", example = "100.0")
    private double oldBalance;

    @Schema(description = "Balance After Transaction", example = "100.0")
    private double newBalance;

    @Schema(description = "Transaction date", pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;

    @Schema(description = "Transaction account", example = "1")
    private Long accountId;
}
