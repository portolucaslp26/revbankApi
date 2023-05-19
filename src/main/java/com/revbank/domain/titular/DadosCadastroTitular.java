package com.revbank.domain.titular;

import com.revbank.domain.conta.DadosConta;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTitular(
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @NotNull
        DadosConta conta
) {
}
