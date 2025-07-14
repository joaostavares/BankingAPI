package com.studyproj.banking.domain.reponses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(name = "Account Response DTO", description = "Account Response DTO Model")
@Getter
@Setter
public class AccountResponse {
    @Schema(description = "Account ID generated automatically", example = "1")
    private Long id;

    @Schema(description = "Account balance", example = "1000.00")
    private double balance;

    @Schema(description = "Account status", example = "true")
    private boolean blocked;

    @Schema(description = "Account type", example = "1")
    private int accountType;

    @Schema(description = "Account creation date", pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate creationDate;
}
