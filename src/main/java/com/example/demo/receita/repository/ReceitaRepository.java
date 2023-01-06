package com.example.demo.receita.repository;

import com.example.demo.receita.entity.Receita;
import com.example.demo.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    List<Receita> findReceitasByUser(User user);
}
