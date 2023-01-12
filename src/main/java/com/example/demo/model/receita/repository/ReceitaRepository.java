package com.example.demo.model.receita.repository;

import com.example.demo.model.receita.entity.Receita;
import com.example.demo.model.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findReceitasByUser(User user);
}
