package application.domain.customer;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class Customer {

    // Identificador único do cliente
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // Dados pessoais
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    // Contato
    @Column(nullable = false)
    private String email;

    private String telephone;

    private String cellphone;

    // Endereço
    private String zipCode;      // CEP
    private String publicPlace;  // Logradouro
    private String number;
    private String complement;

    // Construtor vazio obrigatório para o JPA
    protected Customer() {
    }

    // Construtor para criação do cliente
    public Customer(String name,
                    String cpf,
                    LocalDate dateOfBirth,
                    String email) {

        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    // Getters

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getCellphone() {
        return cellphone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public String getComplement() {
        return complement;
    }
}
