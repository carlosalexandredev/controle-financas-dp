package com.example.demo.despesa.repository;


import com.example.demo.despesa.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
