package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.service.UserService;
import cl.diego.balance.users.app.users.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    @Mock
    private UserService    userService;

    @BeforeEach
    void setUp() {
        userService    = mock( UserServiceImpl.class );
        userController = new UserController( userService );
    }

    @Test
    void createUserTest() {

        // Prepare Data
        User user = User.builder()
                .password( "Tommy" )
                .role( "ADMIN" )
                .build();

        // Set Environment
        doNothing().when( userService ).saveUser( user );

        // Execute
        ResponseEntity reponse = userController.createUser( user );

        // Assertions
        assertEquals( HttpStatus.OK, reponse.getStatusCode() );

        // Verify
        verify( userService, times( 1 ) )
                .saveUser( any( User.class ) );
    }

    @Test
    void createUserTest_error() {

        // Prepare Data
        User user = User.builder()
                .password( "Tommy" )
                .role( "ADMIN" )
                .build();

        // Set Environment
        doThrow( new BadInputException( "" ) ).when( userService ).saveUser( user );

        // Execute
        ResponseEntity reponse = userController.createUser( user );

        // Assertions
        assertEquals( HttpStatus.BAD_REQUEST, reponse.getStatusCode() );

        // Verify
        verify( userService, times( 1 ) )
                .saveUser( any( User.class ) );
    }

    @Test
    void getUserTest() {

        // Prepare data
        User user = User.builder().build();

        // Set environment
        when( userService.getUserByRut( any( String.class ) ) )
                .thenReturn( user );

        // Execute
        ResponseEntity response = userController.getUser( "12345678" );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode() );

        // Verify
        verify( userService, times( 1 ) )
                .getUserByRut( any( String.class ) );
    }

    @Test
    void getUserTest_error() {

        // Prepare data
        User user = User.builder().build();

        // Set environment
        when( userService.getUserByRut( any( String.class ) ) )
                .thenThrow( new UserNotFoundException( "" ) );

        // Execute
        ResponseEntity response = userController.getUser( "12345678" );

        // Assertions
        assertEquals( HttpStatus.NOT_FOUND, response.getStatusCode() );

        // Verify
        verify( userService, times( 1 ) )
                .getUserByRut( any( String.class ) );
    }
}