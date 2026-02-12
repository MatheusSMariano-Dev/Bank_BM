package application.domain.customer;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.UUID;

@Table
@Entity
public class Customer {

    //identificador
    @Id
    @Column(name = "id")
    private UUID id;

    //dados pessoais
    @Column(name = "name")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "dateOfBirth")
    private LocalDate dateOfBirth;

    //contato
    @Column(name = "email")
    private String email;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "cellphone")
    private String cellphone;
    //endereço
    @Column(name = "zipCode") // cep
    private String zipCode;
    @Column(name = "publicPlace") // logradouro
    private String publicPlace;
    @Column(name = "number")
    private String number;
    @Column(name = "complement")
    private String complement;
}
