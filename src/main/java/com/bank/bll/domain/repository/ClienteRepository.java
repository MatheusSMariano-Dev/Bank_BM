package com.bank.bll.domain.repository;

import com.bank.bll.domain.cliente.Conta.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
