package application.domain.transaction;

import application.domain.account.Account;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// Representa uma transação financeira relacionada a uma conta.
@Entity

// Define o nome da tabela no banco como "transactions".
@Table(name = "transactions")
public class Transaction {

    // Chave primária da tabela de transações.
    @Id
    @GeneratedValue
    private UUID id;

    // Muitas transações pertencem a uma conta.
    @ManyToOne

    // Coluna de chave estrangeira que referencia a conta.
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Valor da transação.
    @Column(nullable = false)
    private BigDecimal amount;

    // Tipo da transação (DEPÓSITO, SAQUE, TRANSFERÊNCIA).
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    // Data e hora em que a transação foi criada.
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Construtor protegido exigido pelo JPA.
    protected Transaction() {
    }

    // Cria uma nova transação para uma conta.
    public Transaction(Account account, BigDecimal amount, TransactionType type) {
        this.account = account;
        this.amount = amount;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }

    // Retorna o identificador da transação.
    public UUID getId() {
        return id;
    }

    // Retorna a conta relacionada.
    public Account getAccount() {
        return account;
    }

    // Retorna o valor da transação.
    public BigDecimal getAmount() {
        return amount;
    }

    // Retorna o tipo da transação.
    public TransactionType getType() {
        return type;
    }

    // Retorna a data e hora de criação.
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
