package com.studyproj.banking.domain.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Schema(name = "Person Request DTO", description = "Person Request DTO Model")
@Getter
@Setter
public class PersonRequest {

    @Schema(description = "Person name", example = "Jão")
    @NotEmpty(message = "{person.name}")
    private String name;

    @Schema(description = "Person CPF", example = "123.456.789-00")
    @CPF(message = "{person.cpf}")
    private String cpf;

    @Schema(description = "Person birth date", pattern = "dd-MM-yyyy")
    @Past
    @NotNull(message = "{person.date}")
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate birthDate;

    @Schema(description = "Person account ID")
    @Min(value = 1, message = "{person.account}")
    private long accountId;
}
