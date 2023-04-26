package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.dto.RoleDto;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.repository.mongodb.UserMongoRepository;
import cl.diego.balance.users.app.users.repository.mongodb.domain.Role;
import cl.diego.balance.users.app.users.repository.mongodb.domain.User;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserMongoRepository usersRepository;

    private UserService userService;

    @BeforeEach
    void setUp( ) {
        Validator validator = mock( Validator.class );

        usersRepository = mock( UserMongoRepository.class );
        userService     = new UserServiceImpl( usersRepository, validator );
    }

    @Test
    void saveUserTest_ok( ) {
        // Prepare data
        UserDto user = new UserDto( "Tommy",
                "1", "Carreno",
                "Gonzalez", "tommy@carreno.cl",
                "983714551", "1",
                new RoleDto( "1", "ADMIN" ) );

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
        Role role = Role.builder( )
                .id( "1" )
                .name( "ADMIN" )
                .build( );

        User user = User.builder( )
                .role( role )
                .build( );

        // Set environment
        when( usersRepository.findByRut( anyString( ) ) )
                .thenReturn( user );

        // Execute
        UserDto userDto = userService.getUserByRut( "1-1" );

        // Assertions
        assertNotNull( userDto );

        // Verify
        usersRepository.findByRut( anyString( ) );
    }

    @Test
    void getUserByRutTest_error( ) {
        // Prepare data

        // Set environment
        when( usersRepository.findByRut( anyString( ) ) )
                .thenReturn( null );

        // Execute
        // Assertions
        assertThrows( UserNotFoundException.class, ( ) -> userService.getUserByRut( "1-1" ) );

        // Verify
        usersRepository.findByRut( anyString( ) );
    }

    @Test
    void updateUserTest_ok( ) {
        // Prepare data
        Role role = Role.builder( )
                .id( "1" )
                .name( "ADMIN" )
                .build( );

        User user = User.builder( )
                .rut( "1-1" )
                .role( role )
                .build( );

        UserDto userDto = UserDto.builder( )
                .rut( "1-1" )
                .role( RoleDto.builder( )
                        .id( "1" )
                        .name( "ADMIN" )
                        .build( ) )
                .build( );

        // Set environment
        when( usersRepository.findByRut( "1-1" ) )
                .thenReturn( user );
        when( usersRepository.save( any( User.class ) ) )
                .thenReturn( new User( ) );

        // Execute
        userService.updateUser( userDto );

        //Assertions

        // Verify
        verify( usersRepository ).save( any( User.class ) );
    }

    @Test
    void deleteUserTest_ok( ) {
        // Prepare data

        // Set environment
        doNothing( ).when( usersRepository ).deleteById( anyString( ) );

        // Execute
        userService.deleteUser( 1L );

        //Assertions

        // Verify
        verify( usersRepository ).deleteById( anyString( ) );
    }
}