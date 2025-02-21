package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.Authority;
import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.repository.AuthorityRepository;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.service.AuthenticationService;
import hu.unideb.inf.flashcards.service.JwtAuthService;
import hu.unideb.inf.flashcards.service.dto.LoginDTO;
import hu.unideb.inf.flashcards.service.dto.RegisterDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    UserRepository repo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    AuthorityRepository jogRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager manager;
    @Autowired
    JwtAuthService jwtService;

    @Override
    public String register(RegisterDTO dto) {
        var existingUser = repo.findByUsername(dto.getUsername());
        if (existingUser != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "User already exists.");
        var user = modelMapper.map(dto, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var authority = jogRepo.findByName("USER");
        if (authority == null) {
            authority = new Authority();
            authority.setName("USER");
            authority = jogRepo.save(authority);

            user.setAuthority(authority);
        } else {
            user.setAuthority(authority);
        }
        user = repo.save(user);
        return jwtService.generateToken(user);
    }

    @Override
    public String login(LoginDTO dto) {
        manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername()
                        , dto.getPassword()
                )
        );
        var user = repo.findByUsername(dto.getUsername());
        return jwtService.generateToken(user);
    }
}
