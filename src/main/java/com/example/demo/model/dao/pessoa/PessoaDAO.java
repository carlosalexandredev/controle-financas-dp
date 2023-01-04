package com.example.demo.model.dao.pessoa;

import com.example.demo.model.entity.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaDAO extends JpaRepository<Perfil, Long>{

}
