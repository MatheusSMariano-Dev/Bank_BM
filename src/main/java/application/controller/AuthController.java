package application.controller;

import application.config.JwtService;
import application.controller.dto.user.LoginRequest;
import application.controller.dto.user.RegisterRequest;
import application.domain.account.User;
import application.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtService jwtService;

    public AuthController(UserService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        service.createUser(request.getCpf(),
                request.getAccountNumber(),
                request.getPassword());

        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = service.authenticate(
                request.getLogin(),
                request.getPassword()
        );

        String token = jwtService.generateToken(user.getCpf());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
