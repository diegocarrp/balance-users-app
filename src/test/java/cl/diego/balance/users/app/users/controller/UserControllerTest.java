package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.service.UserService;
import cl.diego.balance.users.app.users.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
        User user = User.builder().build();

        // Set Environment
        when( userService.saveUser( any( User.class ) ) )
                .thenReturn( true );

        // Execute
        // Assertions
        assertTrue( userController.createUser( user ) );

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
        // Assertions
        assertTrue( userController.getUser( "12345678" ) );

        // Verify
        verify( userService, times( 1 ) )
                .getUserByDni( any( String.class ) );
    }
}