package com.example.demo.model.dao.investimento;

import com.example.demo.model.entity.Despesa;
import com.example.demo.model.entity.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestimentoDAO extends JpaRepository<Investimento, Long> {
}
