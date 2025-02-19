package hu.unideb.inf.flashcards.controller;

import hu.unideb.inf.flashcards.service.AuthenticationService;
import hu.unideb.inf.flashcards.service.dto.LoginDTO;
import hu.unideb.inf.flashcards.service.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> handleOptions(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterDTO dto){
        return authenticationService.register(dto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO dto){
        return authenticationService.login(dto);
    }
}
