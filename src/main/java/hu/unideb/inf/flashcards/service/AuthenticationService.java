package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.LoginDTO;
import hu.unideb.inf.flashcards.service.dto.RegisterDTO;
import hu.unideb.inf.flashcards.service.dto.UserResponseDTO;

import java.util.Optional;

public interface AuthenticationService {

    String register(RegisterDTO dto);
    String login(LoginDTO dto);
    Optional<UserResponseDTO> getValidatedUser(String token);
}
