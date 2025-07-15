package Alura.ForumHub.controller;

import Alura.ForumHub.dto.LoginDto;
import Alura.ForumHub.model.Usuario;
import Alura.ForumHub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public String login(@RequestBody @Valid LoginDto dto) {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.email(), dto.senha()));
        return tokenService.gerarToken((Usuario) auth.getPrincipal());
    }
}
