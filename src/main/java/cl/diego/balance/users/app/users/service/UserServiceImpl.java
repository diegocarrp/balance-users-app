package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.repository.UserEntity;
import cl.diego.balance.users.app.users.repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;

    @Override
    public void saveUser( User user ) {
        usersRepository.save( new UserEntity( user ) );
    }

    @Override
    public User getUserByRut( String rut ) {
        if(rut.isEmpty()) {
            log.info( "hola" );
        }
        UserEntity userDb = usersRepository.findByRut( rut )
                .orElseThrow( UserNotFoundException::new );
        log.info("userFound: <{}>", userDb);
        return userDb.toUser();
    }

    @Override
    public void updateUser( User user ) throws BadInputException {
        User userDb = getUserByRut( user.getRut() );
        userDb.updateUser(user);
        usersRepository.save( new UserEntity( user ) );
    }

    @Override
    public void deleteUser( Long id ) {
        usersRepository.deleteById( id );
    }

}
