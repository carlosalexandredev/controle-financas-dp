package com.example.demo.model.despesa.repository;


import com.example.demo.model.despesa.entity.Despesa;
import com.example.demo.model.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    List<Despesa> findDespesaByUser(User user);
}
