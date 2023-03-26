package cl.diego.balance.users.app.users.repository.mongodb;

import cl.diego.balance.users.app.users.repository.mongodb.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleMongoRepository extends MongoRepository<Role, String> {
}
