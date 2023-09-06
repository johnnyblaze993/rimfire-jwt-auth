package com.rimfire.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;

@MappedEntity("jwt_auth.token")
public class Token {

    @Id
    private String token;
    private UUID userId;
    private LocalDateTime exp;
    private LocalDateTime issuedAt;
    private boolean isValid;

    // Getters and setters (generate these using your IDE)
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public UUID getUserId() {
        return userId;
    }
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public LocalDateTime getExp() {
        return exp;
    }
    public void setExp(LocalDateTime exp) {
        this.exp = exp;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }
    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }


    public Token() {
    }

    public Token(String token, UUID userId, LocalDateTime exp, LocalDateTime issuedAt, boolean isValid) {
        this.token = token;
        this.userId = userId;
        this.exp = exp;
        this.issuedAt = issuedAt;
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                ", exp=" + exp +
                ", issuedAt=" + issuedAt +
                ", isValid=" + isValid +
                '}';
    }

}