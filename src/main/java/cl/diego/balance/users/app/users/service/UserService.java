package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.UserDto;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;

public interface UserService {
    void saveUser( UserDto user ) throws BadInputException;

    UserDto getUserByRut( String rut ) throws UserNotFoundException;

    void updateUser( UserDto user ) throws BadInputException;

    void deleteUser( Long id );
}
