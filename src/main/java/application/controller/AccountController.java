package application.controller;

import application.model.Conta;
import application.service.account.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/conta")
public class AccountController {

    private final ContaService contaService;

    public AccountController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<?> criarConta(@RequestBody Conta contaRequest) {
        try {
            Conta contaCriada = contaService.criarConta(
                    contaRequest.getNomeCliente(),
                    contaRequest.getCpf(),
                    contaRequest.getIdade(),
                    contaRequest.getEndereco()
            );
            return new ResponseEntity<Conta>(contaCriada, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<String>("Erro ao criar conta.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
