package com.example.demo.model.dao.despesa;

import com.example.demo.model.entity.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaDAO extends JpaRepository<Despesa, Long> {
}
