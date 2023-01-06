package com.example.demo.investimento.repository;


import com.example.demo.investimento.entity.Investimento;
import com.example.demo.receita.entity.Receita;
import com.example.demo.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    List<Receita> findInvestimentoByUser(User user);
}
