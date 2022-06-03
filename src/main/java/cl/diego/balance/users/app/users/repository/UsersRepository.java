package cl.diego.balance.users.app.users.repository;

import cl.diego.balance.users.app.users.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {

}
