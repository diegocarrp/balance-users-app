package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
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
        when( userService.saveUser( user ) )
                .thenReturn( true );

        // Execute
        ResponseEntity reponse = userController.createUser( user );

        // Assertions
        assertEquals( HttpStatus.CREATED, reponse.getStatusCode() );

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
        when( userService.saveUser( user ) )
                .thenReturn( false );

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
        when( userService.getUserByDni( any( String.class ) ) )
                .thenReturn( user );

        // Execute
        ResponseEntity response = userController.getUser( "12345678" );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode() );

        // Verify
        verify( userService, times( 1 ) )
                .getUserByDni( any( String.class ) );
    }
}