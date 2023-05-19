package com.revbank.domain.conta;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
//@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String agencia;
    private String numero;
    private Double saldo;

    public Conta() {

    }

    public Conta(DadosConta dados) {
        this.agencia = dados.agencia();
        this.numero = dados.numero();
        this.saldo = dados.saldo();
    }

}
