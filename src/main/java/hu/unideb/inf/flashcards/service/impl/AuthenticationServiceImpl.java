package hu.unideb.inf.flashcards.service.impl;

import hu.unideb.inf.flashcards.data.entity.Authority;
import hu.unideb.inf.flashcards.data.entity.UserEntity;
import hu.unideb.inf.flashcards.data.repository.AuthorityRepository;
import hu.unideb.inf.flashcards.data.repository.UserRepository;
import hu.unideb.inf.flashcards.service.AuthenticationService;
import hu.unideb.inf.flashcards.service.JwtAuthService;
import hu.unideb.inf.flashcards.service.UserService;
import hu.unideb.inf.flashcards.service.dto.LoginDTO;
import hu.unideb.inf.flashcards.service.dto.RegisterDTO;
import hu.unideb.inf.flashcards.service.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

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
    @Autowired
    UserService userService;

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
        try {
            manager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getUsername()
                            , dto.getPassword()
                    )
            );
        }
        catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials.");
        }
        var user = repo.findByUsername(dto.getUsername());
        return jwtService.generateToken(user);
    }

    @Override
    public Optional<UserResponseDTO> getValidatedUser(String token) {
        String userName = jwtService.extractUsername(token);
        UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userName);
        if (jwtService.validateToken(token, userDetails)) {
            UserResponseDTO userDto = new UserResponseDTO(
                    userDetails.getUsername(),
                    userDetails.getAuthorities().stream()
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("No authority found"))
                            .getAuthority()
            );
            return Optional.of(userDto);
        }
        return Optional.empty();
    }
}
