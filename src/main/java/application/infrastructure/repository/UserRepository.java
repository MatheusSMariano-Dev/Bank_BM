package application.infrastructure.repository;

import application.domain.account.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional <User> findByCpf(String cpf);

    Optional <User> findByAccountNumber(String accountNumber);

    Optional<User> findFirstByCpfOrAccountNumber(String cpf, String accountNumber);
}
