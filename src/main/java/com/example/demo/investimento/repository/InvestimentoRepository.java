package com.example.demo.investimento.repository;


import com.example.demo.investimento.entity.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
}
