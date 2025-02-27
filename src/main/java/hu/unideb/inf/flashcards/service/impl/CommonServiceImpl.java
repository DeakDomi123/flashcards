package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity getCurrentUser(UserDetails userDetails) {
        var user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null)
            throw new IllegalArgumentException("User not found for username: " + userDetails.getUsername());
        return user;
    }
}