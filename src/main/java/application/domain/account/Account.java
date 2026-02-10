package application.domain.account;

/*
 * EN: Imports the Customer class, which represents the account owner.
 * PT: Importa a classe Customer, que representa o dono da conta.
 */
import application.domain.customer.Customer;

/*
 * EN: JPA annotations used to map this class to a database table.
 * PT: Anotações do JPA usadas para mapear esta classe para uma tabela no banco de dados.
 */
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

/*
 * EN: Marks this class as a JPA Entity (it will become a database table).
 * PT: Marca esta classe como uma Entidade JPA (ela vira uma tabela no banco).
 */
@Entity

/*
 * EN: Defines the name of the database table as "accounts".
 * PT: Define o nome da tabela no banco como "accounts".
 */
@Table(name = "accounts")
public class Account {

    /*
     * EN: Primary key of the account table.
     *     A unique identifier for each account.
     * PT: Chave primária da tabela account.
     *     Identificador único de cada conta.
     */
    @Id
    @GeneratedValue
    private UUID id;

    /*
     * EN: Relationship: many accounts can belong to one customer.
     * PT: Relacionamento: várias contas podem pertencer a um cliente.
     */
    @ManyToOne

    /*
     * EN: Creates the foreign key column "customer_id" in the accounts table.
     * PT: Cria a coluna de chave estrangeira "customer_id" na tabela accounts.
     */
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /*
     * EN: Stores the account balance.
     *     Cannot be null.
     * PT: Armazena o saldo da conta.
     *     Não pode ser nulo.
     */
    @Column(nullable = false)
    private BigDecimal balance;

    /*
     * EN: Stores the account status as a String in the database (ACTIVE, BLOCKED).
     * PT: Armazena o status da conta como texto no banco (ACTIVE, BLOCKED).
     */
    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    /*
     * EN: Protected constructor required by JPA.
     *     Used internally by frameworks.
     * PT: Construtor protegido exigido pelo JPA.
     *     Usado internamente por frameworks.
     */
    protected Account() {
    }

    /*
     * EN: Creates a new account linked to a customer.
     *     The account starts with zero balance and ACTIVE status.
     * PT: Cria uma nova conta vinculada a um cliente.
     *     A conta começa com saldo zero e status ATIVO.
     */
    public Account(Customer customer) {
        this.customer = customer;
        this.balance = BigDecimal.ZERO;
        this.status = AccountStatus.ACTIVE;
    }

    /*
     * EN: Returns the unique identifier of the account.
     * PT: Retorna o identificador único da conta.
     */
    public UUID getId() {
        return id;
    }

    /*
     * EN: Returns the customer who owns this account.
     * PT: Retorna o cliente dono da conta.
     */
    public Customer getCustomer() {
        return customer;
    }

    /*
     * EN: Returns the current account balance.
     * PT: Retorna o saldo atual da conta.
     */
    public BigDecimal getBalance() {
        return balance;
    }

    /*
     * EN: Returns the current account status.
     * PT: Retorna o status atual da conta.
     */
    public AccountStatus getStatus() {
        return status;
    }

    /*
     * EN: Blocks the account, preventing operations.
     * PT: Bloqueia a conta, impedindo operações.
     */
    public void block() {
        this.status = AccountStatus.BLOCKED;
    }

    /*
     * EN: Activates the account, allowing operations again.
     * PT: Ativa a conta novamente, permitindo operações.
     */
    public void activate() {
        this.status = AccountStatus.ACTIVE;
    }
}
