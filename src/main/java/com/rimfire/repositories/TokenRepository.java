package com.rimfire.repositories;

import com.rimfire.entities.Token;
import io.micronaut.data.annotation.Query;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface TokenRepository extends CrudRepository<Token, String> {

    @Query("SELECT * FROM jwt_auth.token")
    List<Token> findAll();

    @Query("SELECT * FROM jwt_auth.token WHERE user_id = :userId")
    List<Token> findByUserId(UUID userId);

    @Query("INSERT INTO jwt_auth.token (token, user_id, exp, issued_at, is_valid) VALUES (:token, :userId, :exp, :issuedAt, :isValid)")
    void saveToken(String token, UUID userId, LocalDateTime exp, LocalDateTime issuedAt, boolean isValid);
}
