package com.revbank.domain.titular;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitularRepository extends JpaRepository<Titular, Long> {
//    @Query("SELECT t FROM Titular t WHERE t.ativo = 1")
    List<Titular> findByAtivoTrue();
}
