package com.revbank.domain.transferencias;

import com.revbank.domain.titular.Titular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    List<Transferencia> findByContaOrigemIdOrContaDestinoId(Long contaOrigemId, Long contaDestinaId);

}
