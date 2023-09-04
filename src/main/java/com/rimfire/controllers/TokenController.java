package com.rimfire.controllers;

import com.rimfire.entities.Token;
import com.rimfire.repositories.TokenRepository;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import jakarta.inject.Inject;
import java.util.List;
import java.util.UUID;

import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;


@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/tokens")
public class TokenController {

    @Inject
    TokenRepository tokenRepository;

    @Get(produces = MediaType.APPLICATION_JSON)
public List<Token> getAllTokens() {
    return tokenRepository.findAll();
}

    @Get(uri="/{userId}", produces=MediaType.APPLICATION_JSON)
    public List<Token> getTokensByUserId(UUID userId) {
        return tokenRepository.findByUserId(userId);
    }

    @Post(produces=MediaType.TEXT_PLAIN)
    public String createToken(@Body Token token) {
        tokenRepository.saveToken(token.getToken(), token.getUserId(), token.getExp(), token.getIssuedAt(), token.getIsValid());
        return "Token created successfully";
    }
}
