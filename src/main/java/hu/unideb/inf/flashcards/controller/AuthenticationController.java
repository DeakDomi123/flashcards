package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.AuthenticationService;
import hu.unideb.inf.flashcards.service.dto.LoginDTO;
import hu.unideb.inf.flashcards.service.dto.RegisterDTO;
import hu.unideb.inf.flashcards.service.dto.UserResponseDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDTO dto, HttpServletResponse response) {
        String token = authenticationService.register(dto);
        setTokenCookie(response, token);
        return buildAuthResponse(token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto, HttpServletResponse response) {
        String token = authenticationService.login(dto);
        setTokenCookie(response, token);
        return buildAuthResponse(token);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt_token", null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserDetails(HttpServletRequest request) {
        String token = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
            for (Cookie cookie : cookies)
                if ("jwt_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
        if (token == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is missing");
        return buildUserResponse(token);
    }


    private void setTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt_token", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(31556926);
        response.addCookie(cookie);
    }

    private ResponseEntity<?> buildAuthResponse(String token) {
        Optional<UserResponseDTO> userDtoOptional = authenticationService.getValidatedUser(token);
        if (userDtoOptional.isPresent())
            return ResponseEntity.ok(Map.of("token", token, "user", userDtoOptional.get()));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private ResponseEntity<?> buildUserResponse(String token) {
        Optional<UserResponseDTO> userDtoOptional = authenticationService.getValidatedUser(token);
        if (userDtoOptional.isPresent())
            return ResponseEntity.ok(userDtoOptional.get());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}