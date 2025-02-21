package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repo;

    @Override
    public UserDetailsService userDetailsService() {
        return username -> repo.findByUsername(username);
    }
}
