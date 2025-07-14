package com.studyproj.banking.domain.reponses;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Schema(name = "Person Response DTO", description = "Person Response DTO Model")
@Getter
@Setter
public class PersonResponse {
    @Schema(description = "Person ID generated automatically", example = "1")
    private long id;

    @Schema(description = "Person name", example = "Jão")
    private String name;

    @Schema(description = "Person CPF", example = "123.456.789-00")
    private String cpf;

    @Schema(description = "Person birth date", pattern = "dd-MM-yyyy")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;

    @Schema(description = "Person account ID")
    private long accountId;
}
