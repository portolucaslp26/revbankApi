package com.revbank.domain.titular;

import com.revbank.domain.conta.Conta;

public record DadosDetalhamentoTitular(
        Long id,
        String nome,
        String cpf,
        Conta conta
) {
    public DadosDetalhamentoTitular(Titular titular) {
        this(titular.getId(), titular.getNome(), titular.getCpf(), titular.getConta());
    }
}
