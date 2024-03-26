package cl.diego.balance.users.app.users.repository.mongodb;

import cl.diego.balance.users.app.users.repository.mongodb.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserMongoRepository extends MongoRepository<User, String> {

    Optional<User> findByRut( String rut);

}
