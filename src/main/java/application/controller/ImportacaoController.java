package application.controller;

import application.infrastructure.repository.ClienteRepository;
import application.model.Cliente;
import application.service.account.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/importacao")
public class ImportacaoController {

    @Autowired
    private CsvImportService csvImportService;

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/clientes")
    public ResponseEntity<String> importarClientes(@RequestParam("file") MultipartFile file) {
        csvImportService.importarClientes(file);
        return ResponseEntity.ok("Clientes importados com sucesso!");
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}