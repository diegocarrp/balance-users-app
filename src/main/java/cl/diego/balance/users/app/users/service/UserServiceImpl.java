package cl.diego.balance.users.app.users.service;

import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.repository.UserEntity;
import cl.diego.balance.users.app.users.repository.UsersRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final Validator       validator;

    public UserServiceImpl( UsersRepository usersRepository ) {
        this.usersRepository = usersRepository;
        this.validator       = Validation.byDefaultProvider( )
                .configure( )
                .messageInterpolator( new ParameterMessageInterpolator( ) )
                .buildValidatorFactory( )
                .getValidator( );
    }

    @Override
    public void saveUser( User user ) {
        validateUser( user );
        usersRepository.save( new UserEntity( user ) );
    }

    @Override
    public User getUserByRut( String rut ) {
        UserEntity userDb = usersRepository.findByRut( rut )
                .orElseThrow( UserNotFoundException::new );
        log.info( "userFound: <{}>", userDb );
        return userDb.toUser( );
    }

    @Override
    public void updateUser( User user ) throws BadInputException {
        User userDb = getUserByRut( user.getRut( ) );
        userDb.updateUser( user );
        usersRepository.save( new UserEntity( user ) );
    }

    @Override
    public void deleteUser( Long id ) {
        usersRepository.deleteById( id );
    }

    private void validateUser( User user ) {
        Set<ConstraintViolation<User>> violations = validator.validate( user );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        if (!descriptions.isEmpty( ))
            throw new ApiValidationException( "User wasn't created because of: ", descriptions );
    }
}
