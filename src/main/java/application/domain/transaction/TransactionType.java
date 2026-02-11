package application.domain.transaction;


// PT: Representa os tipos possíveis de transações no sistema.

public enum TransactionType {

    // PT: Dinheiro adicionado à conta.

    DEPOSIT,

    //PT: Dinheiro retirado da conta.

    WITHDRAW,

    //PT: Dinheiro transferido para outra conta.

    TRANSFER
}
