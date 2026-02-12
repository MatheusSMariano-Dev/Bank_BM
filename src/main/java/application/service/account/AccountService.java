package application.service.account;

import application.domain.account.Account;
import application.domain.transaction.Transaction;
import application.domain.transaction.TransactionType;

import java.math.BigDecimal;
import java.util.Objects;

// Serviço responsável por orquestrar operações entre contas.
// Não altera saldo diretamente.
// Apenas chama os métodos da entidade Account.
public class AccountService {

    // Realiza um depósito na conta
    public void deposit(Account account, BigDecimal amount) {

        Objects.requireNonNull(account, "A conta não pode ser nula");
        Objects.requireNonNull(amount, "O valor não pode ser nulo");

        // A própria entidade executa a regra
        account.deposit(amount);

        // Registra a transação
        new Transaction(account, amount, TransactionType.DEPOSIT);
    }

    // Realiza um saque na conta
    public void withdraw(Account account, BigDecimal amount) {

        Objects.requireNonNull(account, "A conta não pode ser nula");
        Objects.requireNonNull(amount, "O valor não pode ser nulo");

        account.withdraw(amount);

        new Transaction(account, amount, TransactionType.WITHDRAW);
    }

    // Realiza transferência entre duas contas
    public void transfer(Account from, Account to, BigDecimal amount) {

        Objects.requireNonNull(from, "Conta origem não pode ser nula");
        Objects.requireNonNull(to, "Conta destino não pode ser nula");
        Objects.requireNonNull(amount, "Valor não pode ser nulo");

        if (from == to) {
            throw new IllegalArgumentException("Não é possível transferir para a mesma conta");
        }

        from.withdraw(amount);
        to.deposit(amount);

        new Transaction(from, amount, TransactionType.TRANSFER);
        new Transaction(to, amount, TransactionType.TRANSFER);
    }
}

