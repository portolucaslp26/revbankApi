package com.revbank.controller;

import com.revbank.domain.conta.Conta;
import com.revbank.domain.conta.ContaRepository;
import com.revbank.domain.titular.*;
import com.revbank.domain.transferencias.Transferencia;
import com.revbank.domain.transferencias.TransferenciaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/titular")
@CrossOrigin("*")
public class TitularController {

    @Autowired
    private TitularRepository titularRepository;

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ContaRepository contaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DadosCadastroTitular dados, UriComponentsBuilder uriBuilder) {

        var titular = new Titular(dados);
        if (titular.getConta().getSaldo() == null) {
            titular.getConta().setSaldo(0.0);
        }
        titularRepository.save(titular);
        var uri = uriBuilder.path("/titular/{id}").buildAndExpand(titular.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTitular(titular));
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable("id") Long id) {
        var titular = titularRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoTitular(titular));
    }

    @GetMapping
    public ResponseEntity<List<DadosDetalhamentoTitular>> listarTitulares() {
        List<Titular> titulares = titularRepository.findByAtivoTrue();
        List<DadosDetalhamentoTitular> dadosTitulares = titulares.stream()
                .map(DadosDetalhamentoTitular::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dadosTitulares);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody @Valid DadosAtualizaTitular dados) {
        var titular = titularRepository.getReferenceById(dados.id());
        titular.atualizaInfo(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTitular(titular));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var titular = titularRepository.getReferenceById(id);
        titular.inativar();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/transferencias")
    public ResponseEntity<List<Transferencia>> getTransferenciasByTitularId(@PathVariable("id") Long titularId) {
        Titular titular = titularRepository.getReferenceById(titularId);

        if (titular == null) {
            // Verificar se o titular não foi encontrado
            return ResponseEntity.notFound().build();
        }

        // Obter as transferências com base no titular
        List<Transferencia> transferencias = transferenciaRepository.findByContaOrigemIdOrContaDestinoId(titular.getId(), titular.getId());

        return ResponseEntity.ok(transferencias);
    }




}
