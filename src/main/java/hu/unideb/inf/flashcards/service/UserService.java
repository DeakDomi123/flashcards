package hu.unideb.inf.flashcards.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
}
