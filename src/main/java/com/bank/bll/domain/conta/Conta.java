package com.bank.bll.domain.conta;

import com.bank.bll.domain.cliente.Cliente;

import java.math.BigDecimal;
import java.util.UUID;

public class Conta {

    private UUID id;
    private Cliente cliente;
    private BigDecimal saldo;
    private StatusConta status;

    //Construtor protegido para frameworks
    protected Conta() {
    }

    //Construtor oficial do domínio
    public Conta(Cliente cliente) {
        this.id = UUID.randomUUID();
        this.cliente = cliente;
        this.saldo = BigDecimal.ZERO;
        this.status = StatusConta.ATIVA;
    }

    //Consultar saldo
    public BigDecimal consultarSaldo() {
        return saldo;
    }

    //Consultar status
    public StatusConta getStatus() {
        return status;
    }

    //Bloquear conta
    public void bloquear() {
        this.status = StatusConta.BLOQUEADA;
    }

    //Ativar conta
    public void ativar() {
        this.status = StatusConta.ATIVA;
    }

    //Identificadores
    public UUID getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
