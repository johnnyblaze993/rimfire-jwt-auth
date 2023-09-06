package com.rimfire.services;


import io.micronaut.security.token.generator.TokenGenerator;
import jakarta.inject.Singleton;


import com.rimfire.entities.Token;
import com.rimfire.entities.Users;
import com.rimfire.repositories.TokenRepository;
import com.rimfire.repositories.UsersRepository;

import jakarta.inject.Inject;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@Singleton
public class AuthService {
 private static final Logger LOG = LoggerFactory.getLogger(AuthService.class);
    @Inject
    private UsersRepository usersRepository;

    @Inject
    private TokenRepository tokenRepository;

    @Inject
    private TokenGenerator tokenGenerator;

 
    public Users authenticateUser(String username, String firstName) {
        return usersRepository.findByUsernameAndFirstName(username, firstName);
    }

    public Optional<String> generateTokenForUser(String userIdString) {
        UUID userId = UUID.fromString(userIdString);
        LOG.debug("Generating token for user with ID: {}", userId);
        Optional<Users> userOptional = usersRepository.findById(userId);
        if(userOptional.isPresent()) {
            Users user = userOptional.get();
            Map<String, Object> claims = new HashMap<>();
            claims.put("firstName", user.getFirstName());
            claims.put("lastName", user.getLastName());
            claims.put("phoneNumber", user.getPhoneNbr());
            claims.put("userId", user.getUserId());
            claims.put("username", user.getUsername());
            claims.put("timestamp", System.currentTimeMillis());
    
            Optional<String> tokenOptional = tokenGenerator.generateToken(claims);
    
            tokenOptional.ifPresent(s -> {
                Token token = new Token();
                token.setToken(s);
                token.setUserId(userId);
                LocalDateTime issuedAt = LocalDateTime.now();
                LocalDateTime exp = LocalDateTime.now().plusMinutes(60);
                token.setIssuedAt(issuedAt);
                token.setExp(exp);
                token.setIsValid(true);
                tokenRepository.deleteByUserId(userId);
                tokenRepository.saveToken(s, userId, exp, issuedAt, true);  // Fixed the method call here
            });
    
            return tokenOptional;
        }
        return Optional.empty();
    }

    public void handleLogout(String tokenStr) {
        Optional<Token> tokenOptional = tokenRepository.findById(tokenStr);
        if(tokenOptional.isPresent()) {
            tokenRepository.deleteByToken(tokenStr);
        } else {
            throw new RuntimeException("Token not found");
        }
    }
    
    
}
