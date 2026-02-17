package application.service.user;

import application.domain.account.User;
import application.infrastructure.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }
    public User createUser (String cpf, String accountNumber, String password){

        String hash = passwordEncoder.encode(password);

        User user = new User(cpf, accountNumber, hash);

        return repository.save(user);


    }


    public User authenticate (String login, String password){
        User user = repository
                .findByCpfOrAccountNumber(login, login)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));


        if (!passwordEncoder.matches(password, user.getPasswordHash())){
            throw new RuntimeException("Senha inválida");
        }
return user;
    }



}



