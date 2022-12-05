package com.example.demo.model.dao.pessoa;

import com.example.demo.model.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaDAO extends JpaRepository<Pessoa, Long>{

}
