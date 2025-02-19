package hu.unideb.inf.flashcards.service;

import hu.unideb.inf.flashcards.service.dto.LoginDTO;
import hu.unideb.inf.flashcards.service.dto.RegisterDTO;

public interface AuthenticationService {

    String register(RegisterDTO dto);

    String login(LoginDTO dto);
}
