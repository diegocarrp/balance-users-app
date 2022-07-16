package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.repository.domain.User;
import cl.diego.balance.users.app.users.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UsersRepository usersRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp( ) {
        usersRepository = mock( UsersRepository.class );
        userService     = new UserServiceImpl( usersRepository );
    }

    @Test
    void saveUserTest_ok( ) {
        // Prepare data
        UserDto user = new UserDto( "Tommy", "1", "Carreno", "Gonzalez", "tommy@carreno.cl", "983714551", "1", "OLIWI" );

        // Set environment
        when( usersRepository.save( any( User.class ) ) )
                .thenReturn( new User( ) );

        //Execute
        userService.saveUser( user );

        //Assertions

        // Verify
        verify( usersRepository ).save( any( User.class ) );
    }

    @Test
    void getUserByRutTest_ok( ) {
        // Prepare data

        // Set environment
        when( usersRepository.findByRut( anyString( ) ) )
                .thenReturn( Optional.of( new User( ) ) );

        // Execute
        UserDto user = userService.getUserByRut( "1-1" );

        // Assertions
        assertNotNull( user );

        // Verify
        usersRepository.findByRut( anyString( ) );
    }

    @Test
    void getUserByRutTest_error( ) {
        // Prepare data

        // Set environment
        when( usersRepository.findByRut( anyString( ) ) )
                .thenReturn( Optional.empty( ) );

        // Execute
        // Assertions
        assertThrows( UserNotFoundException.class, ( ) -> userService.getUserByRut( "1-1" ) );

        // Verify
        usersRepository.findByRut( anyString( ) );
    }

    @Test
    void updateUserTest_ok( ) {
        // Prepare data
        UserDto user = new UserDto( );
        user.setRut( "1-1" );
        // Set environment
        when( usersRepository.findByRut( "1-1" ) )
                .thenReturn( Optional.of( new User( ) ) );
        when( usersRepository.save( any( User.class ) ) )
                .thenReturn( new User( ) );

        // Execute
        userService.updateUser( user );

        //Assertions

        // Verify
        verify( usersRepository ).save( any( User.class ) );
    }

    @Test
    void deleteUserTest_ok( ) {
        // Prepare data

        // Set environment
        doNothing( ).when( usersRepository ).deleteById( anyLong( ) );

        // Execute
        userService.deleteUser( 1L );

        //Assertions

        // Verify
        verify( usersRepository ).deleteById( anyLong( ) );
    }
}