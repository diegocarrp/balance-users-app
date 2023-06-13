package cl.diego.balance.users.app.users.repository.mongodb;

import cl.diego.balance.users.app.users.repository.mongodb.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findByRut( String rut);

}
