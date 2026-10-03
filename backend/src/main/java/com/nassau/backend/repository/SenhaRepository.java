package com.nassau.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nassau.backend.model.Senha;

import java.util.List;

@Repository
public interface SenhaRepository extends JpaRepository<Senha, Long> {

    List<Senha> findByStatusOrderByDataHoraEmissaoAsc(String status);

    long countByTipo(String tipo);
}