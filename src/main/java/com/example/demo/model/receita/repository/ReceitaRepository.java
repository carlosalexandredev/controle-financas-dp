package com.example.demo.model.receita.repository;

import com.example.demo.model.receita.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}
