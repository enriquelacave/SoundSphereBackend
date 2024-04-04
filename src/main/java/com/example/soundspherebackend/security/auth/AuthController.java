package com.example.soundspherebackend.security.auth;

import com.example.soundspherebackend.dto.LoginDTO;
import com.example.soundspherebackend.model.Login;
import com.example.soundspherebackend.model.Tokens;
import com.example.soundspherebackend.security.service.JwtService;
import com.example.soundspherebackend.service.LoginService;
import com.example.soundspherebackend.service.TokensService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokensService tokensService;

    @PostMapping("/login")
    public AuthDTO login(@RequestBody LoginDTO loginDTO) {
        Login login = (Login) loginService.loadUserByUsername(loginDTO.getUsername());
        String tokenUsuario = null;


        if(login.getTokens() == null) {
            tokenUsuario = jwtService.generateToken(login);
            Tokens token = new Tokens();
            token.setLogin(login);
            token.setTokenUsuario(tokenUsuario);
            token.setFechaExpiracion(LocalDateTime.now().plusDays(3));

            tokensService.save(token);

        }else if(login.getTokens().getFechaExpiracion().isBefore(LocalDateTime.now())){
            Tokens tokens = login.getTokens();
            tokenUsuario = jwtService.generateToken(login);
            tokens.setTokenUsuario(tokenUsuario);
            tokens.setFechaExpiracion(LocalDateTime.now().plusDays(3));
            tokensService.save(tokens);
        }else{
            tokenUsuario = login.getTokens().getTokenUsuario();
        }

        return AuthDTO
                .builder()
                .token(tokenUsuario)
                .rol(login.getRol().name())
                .idLogin(login.getId())
                .info("Usuario logeado correctamente")
                .build();
    }
    @PostMapping("/register")
    public AuthDTO register(@RequestBody LoginDTO loginDTO) {
        Login loginNuevo = loginService.save(loginDTO);
        String token = jwtService.generateToken(loginNuevo);

        return AuthDTO
                .builder()
                .token(token)
                .info("Login creado correctamente")
                .build();
    }

}
