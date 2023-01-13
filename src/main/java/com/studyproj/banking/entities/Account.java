package com.studyproj.banking.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Schema(name = "Account",description = "Account Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Schema(description = "Account ID generated automatically", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Account balance", example = "1000.00")
    @NonNull
    private double balance;

    @Schema(description = "Account status", example = "true")
    @NonNull
    private boolean flagged;

    @Schema(description = "Account type", example = "1")
    @NonNull
    private int accountType;

    @Schema(description = "Account creation date", pattern = "dd-MM-yyyy")
    @NonNull
    private LocalDate creationDate;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonBackReference
    private Person person;
}
