package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.PersonDto;
import cl.diego.balance.users.app.users.domain.UserDto;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.PersonNotFoundException;

public interface PersonService {

    void savePerson( PersonDto person ) throws BadInputException;

    UserDto getPersonByRut( String rut ) throws PersonNotFoundException;

    void updatePerson( PersonDto person ) throws BadInputException;

    void deletePerson( Long id );
}
