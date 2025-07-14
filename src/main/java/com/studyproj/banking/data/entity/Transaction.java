package com.studyproj.banking.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(name = "Transaction Entity", description = "Transaction Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transaction {
    @Schema(description = "Transaction ID generated automatically", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Transaction value", example = "100.00")
    private double amount;

    @Schema(description = "Transaction date", pattern = "dd-MM-yyyy")
    private LocalDate transactionDate;

    @Schema(description = "Transaction Type", example = "withdraw")
    @Pattern(regexp = "withdraw|deposit", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String transactionType;

    @Schema(description = "Balance Before Transaction", example = "100.0")
    private double oldBalance;

    @Schema(description = "Balance After Transaction", example = "100.0")
    private double newBalance;

    @ManyToOne
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;

}
