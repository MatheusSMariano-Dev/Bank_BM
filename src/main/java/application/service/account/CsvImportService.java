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

    public String importarClientes(MultipartFile file) {

        int linhaNumero = 1;

        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(file.getInputStream()));

            String linha;

            // Pula o cabeçalho
            reader.readLine();

            List<Cliente> clientes = new ArrayList<>();

            while ((linha = reader.readLine()) != null) {
                linhaNumero++;

                String[] dados = linha.split(",");

                //  VALIDAÇÃO DE COLUNAS
                if (dados.length < 4) {
                    throw new RuntimeException("Erro na linha " + linhaNumero + ": dados incompletos");
                }

                //  VALIDAÇÃO DE CAMPOS
                if (dados[0].isEmpty() || dados[1].isEmpty() || dados[2].isEmpty()) {
                    throw new RuntimeException("Erro na linha " + linhaNumero + ": campos obrigatórios vazios");
                }

                Cliente cliente = new Cliente();

                cliente.setNome(dados[0]);
                cliente.setEmail(dados[1]);
                cliente.setCpf(dados[2]);

                try {
                    cliente.setSaldo(Double.parseDouble(dados[3]));
                } catch (NumberFormatException e) {
                    throw new RuntimeException("Erro na linha " + linhaNumero + ": saldo inválido");
                }

                clientes.add(cliente);
            }

            clienteRepository.saveAll(clientes);

            return "Importação realizada com sucesso. Total: " + clientes.size();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao importar CSV: " + e.getMessage());
        }
    }
}