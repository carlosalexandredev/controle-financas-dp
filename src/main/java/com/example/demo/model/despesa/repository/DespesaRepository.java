package com.example.demo.model.despesa.repository;


import com.example.demo.model.despesa.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
