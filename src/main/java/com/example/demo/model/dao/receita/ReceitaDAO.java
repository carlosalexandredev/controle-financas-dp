package com.example.demo.model.dao.receita;

import com.example.demo.model.entity.Despesa;
import com.example.demo.model.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaDAO extends JpaRepository<Receita, Long> {
}
