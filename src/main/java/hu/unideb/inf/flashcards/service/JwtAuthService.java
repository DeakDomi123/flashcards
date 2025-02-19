package hu.unideb.inf.flashcards.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtAuthService {

    String extractUsername(String token);
    String generateToken(UserDetails userDetails);
    boolean validateToken(String token, UserDetails userDetails);
}
