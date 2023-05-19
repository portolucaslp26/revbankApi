package com.revbank.domain.conta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosConta(

        @NotBlank
        String agencia,
        @NotBlank
        String numero,
        Double saldo
) {
}
