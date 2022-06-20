package cl.diego.balance.users.app.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByRut( String rut );
}
