package application.domain.transaction;

import application.domain.account.Account;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

// Representa uma transação financeira relacionada a uma conta.
@Entity
@Table(name = "transactions")
public class Transaction {

    // Identificador único da transação.
    @Id
    @GeneratedValue
    private UUID id;

    // Muitas transações pertencem a uma conta.
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Valor da transação.
    @Column(nullable = false)
    private BigDecimal amount;

    // Tipo da transação (DEPOSIT, WITHDRAW, TRANSFER).
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType type;

    // Data e hora da criação.
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // Construtor exigido pelo JPA.
    protected Transaction() {
    }

    // Cria uma nova transação validando os dados.
    public Transaction(Account account, BigDecimal amount, TransactionType type) {

        if (account == null) {
            throw new IllegalArgumentException("Conta não pode ser nula");
        }

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor da transação deve ser positivo");
        }

        if (type == null) {
            throw new IllegalArgumentException("Tipo da transação não pode ser nulo");
        }

        this.account = account;
        this.amount = amount;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
