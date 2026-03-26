package application.service.account;

import application.model.Conta;

import java.util.Random;

// Serviço responsável pelas regras de criação de conta bancária simples.
public class ContaService {

    private static final String AGENCIA_PADRAO = "0001";

    public Conta criarConta(String nome, String cpf, int idade, String endereco) {

        validarIdade(idade);

        Conta conta = new Conta();
        conta.setNomeCliente(nome);
        conta.setCpf(cpf);
        conta.setIdade(idade);
        conta.setEndereco(endereco);
        conta.setAgencia(AGENCIA_PADRAO);
        conta.setNumero(gerarNumeroConta());

        return conta;
    }

    private void validarIdade(int idade) {
        if (idade <= 18) {
            throw new IllegalArgumentException("Não é permitido criar conta para menores de idade. Futuramente existirá conta KID.");
        }
    }

    private String gerarNumeroConta() {
        Random random = new Random();
        int numero = random.nextInt(900000) + 100000;
        return String.valueOf(numero);
    }
}

