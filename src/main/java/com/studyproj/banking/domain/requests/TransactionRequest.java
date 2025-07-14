package com.studyproj.banking.domain.requests;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Transaction Request DTO", description = "Transaction Request DTO Model")
@Getter
@Setter
public class TransactionRequest {

    @Schema(description = "Transaction Value", example = "100.00")
    @DecimalMin(value = "0.1", message = "{transaction.value}")
    private double amount;


    @Schema(description = "Transaction Account", example = "1")
    @Min(value = 1, message = "{transaction.account}")
    private long accountId;

    @Schema(description = "Transaction ID generated automatically", example = "1")
    @JsonIgnore
    private Long id;
}
