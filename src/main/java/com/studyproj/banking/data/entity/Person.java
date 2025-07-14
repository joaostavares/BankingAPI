package com.studyproj.banking.data.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Schema(name = "Person",description = "Person Model")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person {
    @Schema(description = "Person ID generated automatically", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Person name", example = "Jão")
    private String name;

    @Schema(description = "Person CPF", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Person birth date", pattern = "dd-MM-yyyy")
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "idAccount", referencedColumnName = "id")
    @JsonManagedReference
    private Account account;
}
