package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.User;

public interface UserService {
    void saveUser(User user );

    User getUserByDni( String dni );
}
