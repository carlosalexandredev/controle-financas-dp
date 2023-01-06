package com.example.demo.model.usuario.repository;

import com.example.demo.model.usuario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
