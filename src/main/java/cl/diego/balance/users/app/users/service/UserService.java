package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;

public interface UserService {
    void saveUser( User user ) throws BadInputException;

    User getUserByRut( String dni ) throws UserNotFoundException;

    void updateUser( User user ) throws BadInputException;

    void deleteUser( Long id );
}
