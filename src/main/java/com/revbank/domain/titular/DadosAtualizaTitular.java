package com.revbank.domain.titular;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaTitular(
        @NotNull
        Long id,
        String nome
) {
}
