package com.example.demo.despesa.repository;


import com.example.demo.despesa.entity.Despesa;
import com.example.demo.receita.entity.Receita;
import com.example.demo.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Receita> findReceitasByUser(User user);
}
