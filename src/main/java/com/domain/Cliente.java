package com.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Table
@Entity
public class Cliente {

    //identificador
    @Id
    @Column(name = "id")
    private String id;

    //dados pessoais
    @Column(name = "nome")
    private String name;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;

    //contato
    @Column(name = "email")
    private String email;
    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cep")
    private String cep;
    @Column(name = "logradouro")
    private String logradouro;
    @Column(name = "numero")
    private String numero;
    @Column(name = "complemento")
    private String complemento;


}
