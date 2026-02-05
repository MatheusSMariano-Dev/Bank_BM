package com.bank.bll.domain.cliente;

import java.util.UUID;

public class Cliente {

    private UUID id;
    private String nome;
    private String documento;
    private boolean ativo;

    //Construtor protegido
    protected Cliente() {
    }

    //Construtor oficial do domínio
    public Cliente(String nome, String documento) {
        this.id = UUID.randomUUID();
        this.nome = nome;
        this.documento = documento;
        this.ativo = true;
    }

    //Getters
    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDocumento() {
        return documento;
    }

    public boolean isAtivo() {
        return ativo;
    }

    // 🔒 Regras de negócio
    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }
}
