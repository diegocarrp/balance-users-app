package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.repository.UserEntity;
import cl.diego.balance.users.app.users.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;

    @Override
    public void saveUser( User user ) {

        try {
            UserEntity userEntity = UserEntity.builder()
                    .rut( user.getRut() )
                    .names( user.getNames() )
                    .lastname1( user.getLastname1() )
                    .lastname2( user.getLastname2() )
                    .email( user.getEmail() )
                    .role( user.getRole() )
                    .password( user.getPassword() )
                    .cellphone( user.getCellphone() )
                    .build();

            usersRepository.save( userEntity );
        } catch (Exception e) {
            throw new BadInputException( "Error saving the user" );
        }

    }

    @Override
    public User getUserByDni( String dni ) {
        return null;
    }

}
