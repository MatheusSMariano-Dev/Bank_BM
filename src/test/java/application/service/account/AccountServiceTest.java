package application.service.account;

import application.domain.account.Account;
import application.domain.customer.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Classe responsável por testar as regras de negócio do AccountService
class AccountServiceTest {

    // Instância do serviço que será testado
    private AccountService accountService;

    // Contas utilizadas nos cenários de teste
    private Account account1;
    private Account account2;

    // Método executado antes de cada teste
    // Responsável por preparar o cenário inicial
    @BeforeEach
    void setup() {
        // Instancia o serviço
        accountService = new AccountService();

        // Cria um cliente fictício
        Customer customer = new Customer(
                "João",
                "12345678900",
                LocalDate.of(1995, 5, 10),
                "joao@email.com"
        );

        // Cria duas contas associadas ao mesmo cliente
        account1 = new Account(customer);
        account2 = new Account(customer);
    }

    // Testa se o método deposit aumenta corretamente o saldo
    @Test
    void deposit_shouldIncreaseBalance() {

        // Realiza depósito de 100
        accountService.deposit(account1, new BigDecimal("100"));

        // Verifica se o saldo foi atualizado
        assertEquals(new BigDecimal("100"), account1.getBalance());
    }

    // Testa se o método withdraw reduz corretamente o saldo
    @Test
    void withdraw_shouldDecreaseBalance() {

        // Deposita 200
        accountService.deposit(account1, new BigDecimal("200"));

        // Realiza saque de 50
        accountService.withdraw(account1, new BigDecimal("50"));

        // Verifica se o saldo final é 150
        assertEquals(new BigDecimal("150"), account1.getBalance());
    }

    // Testa se o saque lança exceção quando o saldo é insuficiente
    @Test
    void withdraw_shouldThrowException_whenInsufficientBalance() {

        // Deposita apenas 50
        accountService.deposit(account1, new BigDecimal("50"));

        // Tenta sacar 100 e espera uma exceção
        assertThrows(IllegalArgumentException.class, () ->
                accountService.withdraw(account1, new BigDecimal("100"))
        );
    }

    // Testa se a transferência move corretamente o valor entre contas
    @Test
    void transfer_shouldMoveMoneyBetweenAccounts() {

        // Deposita 300 na conta de origem
        accountService.deposit(account1, new BigDecimal("300"));

        // Transfere 100 para a segunda conta
        accountService.transfer(account1, account2, new BigDecimal("100"));

        // Verifica saldo da conta de origem
        assertEquals(new BigDecimal("200"), account1.getBalance());

        // Verifica saldo da conta de destino
        assertEquals(new BigDecimal("100"), account2.getBalance());
    }
}
