package application.controller;

import application.controller.dto.user.LoginRequest;
import application.controller.dto.user.RegisterRequest;
import application.domain.account.User;
import application.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


//essa classe é um controller
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        service.createUser(request.getCpf(),
                request.getAccountNumber(),
                request.getPassword());

        return ResponseEntity.ok("Usuário criado com sucesso");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){

        User user = service.authenticate(
                request.getLogin(),
                request.getPassword()
        );
        return ResponseEntity.ok("Login realizado");
}



}
