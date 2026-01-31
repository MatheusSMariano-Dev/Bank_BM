package com.bank.bll.domain.cliente.repository;

import com.bank.bll.domain.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, UUID> {
}
