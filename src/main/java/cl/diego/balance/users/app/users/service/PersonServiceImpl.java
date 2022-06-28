package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.PersonDto;
import cl.diego.balance.users.app.users.domain.UserDto;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.PersonNotFoundException;
import cl.diego.balance.users.app.users.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public void savePerson( PersonDto person ) throws BadInputException {

    }

    @Override
    public UserDto getPersonByRut( String rut ) throws PersonNotFoundException {
        return null;
    }

    @Override
    public void updatePerson( PersonDto person ) throws BadInputException {

    }

    @Override
    public void deletePerson( Long id ) {

    }
}
