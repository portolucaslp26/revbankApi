package com.revbank.domain.transferencias;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "Transferencia")
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private Double valor;
    private LocalDateTime dataTransferencia;

}