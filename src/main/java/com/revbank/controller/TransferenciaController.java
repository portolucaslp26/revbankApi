package com.revbank.controller;

import com.revbank.domain.conta.Conta;
import com.revbank.domain.conta.ContaRepository;
import com.revbank.domain.transferencias.Transferencia;
import com.revbank.domain.transferencias.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/transferencia")
@CrossOrigin("*")
public class TransferenciaController {

    @Autowired
    ContaRepository contaRepository;

    @Autowired
    TransferenciaRepository transferenciaRepository;

    @PostMapping
    public ResponseEntity realizarTransferencia(@RequestBody Transferencia transferencia) {
        // Obter as contas de origem e destino da transferência
        Conta contaOrigem = contaRepository.getReferenceById(transferencia.getContaOrigemId());
        Conta contaDestino = contaRepository.getReferenceById(transferencia.getContaDestinoId());

        // Verificar se as contas existem
        if (contaOrigem == null || contaDestino == null) {
            return ResponseEntity.badRequest().body("Conta de origem ou conta de destino não encontrada");
        }

        // Verificar se a conta de origem possui saldo suficiente para a transferência
        if (contaOrigem.getSaldo() < transferencia.getValor()) {
            return ResponseEntity.badRequest().body("Saldo insuficiente na conta de origem");
        }

        // Realizar a transferência
        contaOrigem.setSaldo(contaOrigem.getSaldo() - transferencia.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + transferencia.getValor());


        // Definir a data da transferência antes de salvar
        transferencia.setDataTransferencia(LocalDateTime.now());

        // Salvar as alterações no banco de dados
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        // Salvar a transferência no banco de dados
        transferenciaRepository.save(transferencia);

        return ResponseEntity.ok("Transferência realizada com sucesso");
    }


    @GetMapping
    public ResponseEntity<List<Transferencia>> getAllTransferencias() {
        List<Transferencia> transferencias = transferenciaRepository.findAll(Sort.by(Sort.Direction.DESC, "dataTransferencia"));
        return ResponseEntity.ok(transferencias);
    }


}
