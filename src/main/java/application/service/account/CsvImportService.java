package application.service.account;

import application.infrastructure.repository.ClienteRepository;
import application.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    @Autowired
    private ClienteRepository clienteRepository;

    public void importarClientes(MultipartFile file) {

        try {

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(file.getInputStream()));

            String linha;

            reader.readLine();

            List<Cliente> clientes = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {

                String[] dados = linha.split(",");

                Cliente cliente = new Cliente();

                cliente.setNome(dados[0]);
                cliente.setEmail(dados[1]);
                cliente.setCpf(dados[2]);
                cliente.setSaldo(Double.parseDouble(dados[3]));

                clientes.add(cliente);
            }

            clienteRepository.saveAll(clientes);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar CSV");
        }
    }
}