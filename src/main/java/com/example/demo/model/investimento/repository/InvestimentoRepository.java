package com.example.demo.model.investimento.repository;


import com.example.demo.model.investimento.entity.Investimento;
import com.example.demo.model.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    List<Investimento> findInvestimentoByUser(User user);
}
