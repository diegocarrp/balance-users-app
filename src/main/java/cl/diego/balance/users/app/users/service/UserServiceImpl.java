package cl.diego.balance.users.app.users.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.repository.mongodb.UserMongoRepository;
import cl.diego.balance.users.app.users.repository.mongodb.domain.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserMongoRepository usersRepository;
    private final Validator      validator;

    public UserServiceImpl( UserMongoRepository usersRepository,
                            Validator validator ) {
        this.usersRepository = usersRepository;

        this.validator = validator;
    }

    @Override
    public void saveUser( UserDto user ) {
        validateUser( user );
        usersRepository.save( new User( user ) );
    }

    @Override
    public UserDto getUserByRut( String rut ) {
        User userDb = usersRepository.findByRut( rut );
        if (userDb == null) {
            throw new UserNotFoundException();
        }
        log.info( "userFound: <{}>", userDb );
        return userDb.toUser( );
    }

    @Override
    public void updateUser( UserDto user ) throws BadInputException {
        UserDto userDb = getUserByRut( user.getRut( ) );
        userDb.updateUser( user );
        usersRepository.save( new User( userDb ) );
    }

    @Override
    public void deleteUser( Long id ) {
        usersRepository.deleteById( id.toString() );
    }

    private void validateUser( UserDto user ) {
        Set<ConstraintViolation<UserDto>> violations = validator.validate( user );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        if( !descriptions.isEmpty( ) ){
            throw new ApiValidationException( "User wasn't created because of: ", descriptions );
        }
    }
}
