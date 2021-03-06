package com.stagproj.BankingAPI.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String nome;

    @NonNull
    private String cpf;

    @NonNull
    private LocalDate dataNascimento;

    @OneToOne
    @JoinColumn(name = "idConta", referencedColumnName = "id")
    @JsonManagedReference
    private Conta conta;
}
