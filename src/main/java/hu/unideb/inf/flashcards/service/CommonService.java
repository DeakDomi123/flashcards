package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.data.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

public interface CommonService {
    UserEntity getCurrentUser(UserDetails userDetails);
}
