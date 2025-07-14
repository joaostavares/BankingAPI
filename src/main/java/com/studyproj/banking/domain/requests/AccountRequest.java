package com.studyproj.banking.domain.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;


@Schema(name = "Account Request DTO", description = "Account Request DTO Model")
@Getter
@Setter
public class AccountRequest {
    @Schema(description = "Account balance", example = "1000.00")
    private double balance;

    @Schema(description = "Account type", example = "1")
    @Range(min = 1, max = 9, message = "{account.type}")
    private int accountType;
}
