package cl.diego.balance.users.app.users.repository;

import cl.diego.balance.users.app.users.repository.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByRut( String rut );
}