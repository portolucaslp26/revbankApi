package com.revbank.domain.titular;

import com.revbank.domain.conta.Conta;
import com.revbank.domain.conta.DadosConta;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "titulares")
@Entity(name = "Titular")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Titular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Boolean ativo;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "conta_id", referencedColumnName = "id")
    private Conta conta;


    public Titular(DadosCadastroTitular dados) {
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.conta = new Conta(dados.conta());
        this.ativo = true;
    }

    public void atualizaInfo(DadosAtualizaTitular dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
    }

    public void inativar() {
        this.ativo = false;
    }
}
