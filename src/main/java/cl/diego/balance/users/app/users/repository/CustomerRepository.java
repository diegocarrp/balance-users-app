package cl.diego.balance.users.app.users.repository;

import cl.diego.balance.users.app.users.repository.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByRut( String rut );
}
