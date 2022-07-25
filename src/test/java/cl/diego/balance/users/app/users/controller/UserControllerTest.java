package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.commons.rest.exception.ApiException;
import cl.diego.balance.users.app.users.dto.RoleDto;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.service.UserService;
import cl.diego.balance.users.app.users.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserControllerTest {

    private UserController userController;
    @Mock
    private UserService    userService;

    @BeforeEach
    void setUp( ) {
        userService    = mock( UserServiceImpl.class );
        userController = new UserController( userService );
    }

    @Test
    void createUserTest_ok( ) {

        // Prepare Data
        RoleDto role = RoleDto.builder()
                .id( 1L )
                .name( "ADMIN" )
                .build();

        UserDto user = UserDto.builder( )
                .password( "Tommy" )
                .role( role )
                .build( );

        // Set Environment
        doNothing( ).when( userService ).saveUser( user );

        // Execute
        ResponseEntity reponse = userController.createUser( user );

        // Assertions
        assertEquals( HttpStatus.OK, reponse.getStatusCode( ) );

        // Verify
        verify( userService, times( 1 ) )
                .saveUser( any( UserDto.class ) );
    }

    @Test
    void createUserTest_error( ) {

        // Prepare Data
        RoleDto role = RoleDto.builder()
                .id( 1L )
                .name( "ADMIN" )
                .build();

        UserDto user = UserDto.builder( )
                .password( "Tommy" )
                .role( role )
                .build( );

        // Set Environment
        doThrow( new BadInputException( ) ).when( userService ).saveUser( user );

        // Execute
        // Assertions
        assertThrows( ApiException.class, ( ) -> userController.createUser( user ) );

        // Verify
        verify( userService, times( 1 ) )
                .saveUser( any( UserDto.class ) );
    }

    @Test
    void getUserTest_ok( ) {

        // Prepare data
        UserDto user = UserDto.builder( ).build( );

        // Set environment
        when( userService.getUserByRut( any( String.class ) ) )
                .thenReturn( user );

        // Execute
        ResponseEntity response = userController.getUser( "12345678" );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );

        // Verify
        verify( userService, times( 1 ) )
                .getUserByRut( any( String.class ) );
    }

    @Test
    void getUserTest_error( ) {

        // Prepare data
        UserDto user = UserDto.builder( ).build( );

        // Set environment
        when( userService.getUserByRut( any( String.class ) ) )
                .thenThrow( new UserNotFoundException( ) );

        // Execute
        // Assertions
        assertThrows( ApiException.class, () -> userController.getUser( "12345678" ) );

        // Verify
        verify( userService, times( 1 ) )
                .getUserByRut( any( String.class ) );
    }

    @Test
    void updateUserTest_ok( ) {

        // Prepare Data
        RoleDto role = RoleDto.builder()
                .id( 1L )
                .name( "ADMIN" )
                .build();

        UserDto user = UserDto.builder( )
                .password( "Tommy" )
                .role( role )
                .build( );

        // Set Environment
        doNothing( ).when( userService ).updateUser( user );

        // Execute
        ResponseEntity reponse = userController.updateUser( user );

        // Assertions
        assertEquals( HttpStatus.OK, reponse.getStatusCode( ) );

        // Verify
        verify( userService, times( 1 ) )
                .updateUser( any( UserDto.class ) );
    }

    @Test
    void updateUserTest_error( ) {

        // Prepare Data
        RoleDto role = RoleDto.builder()
                .id( 1L )
                .name( "ADMIN" )
                .build();

        UserDto user = UserDto.builder( )
                .password( "Tommy" )
                .role( role )
                .build( );

        // Set Environment
        doThrow( new BadInputException( ) ).when( userService ).updateUser( user );

        // Execute
        // Assertions
        assertThrows( ApiException.class, ( ) -> userController.updateUser( user ) );

        // Verify
        verify( userService, times( 1 ) )
                .updateUser( any( UserDto.class ) );
    }

    @Test
    void deleteUserTest_ok( ) {

        // Prepare data
        UserDto user = UserDto.builder( ).build( );

        // Set environment
        doNothing( ).when( userService ).deleteUser( anyLong( ) );

        // Execute
        ResponseEntity response = userController.deleteUser( 1L );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );

        // Verify
        verify( userService, times( 1 ) )
                .deleteUser( anyLong( ) );
    }

    @Test
    void deleteUserTest_error( ) {

        // Prepare data

        // Set environment
        doThrow( new UserNotFoundException( ) ).when( userService ).deleteUser( anyLong( ) );

        // Execute
        // Assertions
        assertThrows( ApiException.class, ( ) -> userController.deleteUser( 1L ) );

        // Verify
        verify( userService, times( 1 ) )
                .deleteUser( anyLong( ) );
    }
}