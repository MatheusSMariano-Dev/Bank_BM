package application.domain.account;

import application.domain.customer.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

// Entidade que representa uma conta bancária.
// Esta classe será mapeada como uma tabela no banco de dados.
@Entity
@Table(name = "accounts")
public class Account {

    // Identificador único da conta (chave primária).
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Relacionamento: várias contas podem pertencer a um cliente.
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // Saldo atual da conta.
    // Não pode ser nulo.
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    // Status da conta (ACTIVE ou BLOCKED).
    // Será salvo como texto no banco.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    // Construtor protegido exigido pelo JPA.
    protected Account() {
    }

    // Cria uma nova conta vinculada a um cliente.
    // Toda conta inicia com saldo zero e status ACTIVE.
    public Account(Customer customer) {

        if (customer == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        this.customer = customer;
        this.balance = BigDecimal.ZERO;
        this.status = AccountStatus.ACTIVE;
    }

    // Retorna o ID da conta.
    public UUID getId() {
        return id;
    }

    // Retorna o cliente dono da conta.
    public Customer getCustomer() {
        return customer;
    }

    // Retorna o saldo atual.
    public BigDecimal getBalance() {
        return balance;
    }

    // Retorna o status da conta.
    public AccountStatus getStatus() {
        return status;
    }

    // Realiza um depósito na conta.
    // Apenas contas ativas podem receber depósito.
    // O valor deve ser positivo.
    public void deposit(BigDecimal amount) {

        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Conta não está ativa");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo");
        }

        this.balance = this.balance.add(amount);
    }

    // Realiza um saque na conta.
    // Verifica se a conta está ativa.
    // Verifica se o valor é positivo.
    // Verifica se há saldo suficiente.
    public void withdraw(BigDecimal amount) {

        if (status != AccountStatus.ACTIVE) {
            throw new IllegalStateException("Conta não está ativa");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo");
        }

        if (this.balance.compareTo(amount) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }

        this.balance = this.balance.subtract(amount);
    }

    // Bloqueia a conta, impedindo operações.
    public void block() {
        this.status = AccountStatus.BLOCKED;
    }

    // Ativa a conta novamente.
    public void activate() {
        this.status = AccountStatus.ACTIVE;
    }
}
