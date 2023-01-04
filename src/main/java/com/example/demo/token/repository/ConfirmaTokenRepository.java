package com.example.demo.token.repository;

import com.example.demo.token.entity.ConfirmaToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmaTokenRepository
        extends JpaRepository<ConfirmaToken, Long> {

    Optional<ConfirmaToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmaToken c " +
            "SET c.confirmadoEm = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
