package com.example.demo.model.investimento.repository;


import com.example.demo.model.investimento.entity.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
}
