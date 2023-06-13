package cl.diego.balance.users.app.users.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;

public interface UserService {
    void saveUser( UserDto user ) throws BadInputException;

    UserDto getUserByRut( String rut ) throws UserNotFoundException;
    UserDto getUserById( String id ) throws UserNotFoundException;

    void updateUser( UserDto user ) throws BadInputException;

    void deleteUser( Long id );
}
