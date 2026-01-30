package com.bank.bll.domain.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Cliente {

    @Id
    private UUID id;

}
