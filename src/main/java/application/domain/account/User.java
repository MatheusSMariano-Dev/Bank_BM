package application.domain.account;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "account_number")
    private String accountNumber;


    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    private Boolean active = true;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;


    protected User(){
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }



    public User(String cpf, String accountNumber, String passwordHash){
        this.cpf = cpf;
        this.accountNumber = accountNumber;
        this.passwordHash = passwordHash;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}


