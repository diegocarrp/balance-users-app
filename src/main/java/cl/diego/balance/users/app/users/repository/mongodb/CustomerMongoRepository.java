package cl.diego.balance.users.app.users.repository.mongodb;

import cl.diego.balance.users.app.users.repository.mongodb.domain.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerMongoRepository extends MongoRepository<Customer, String> {

    Customer findByRut( String rut);

}
