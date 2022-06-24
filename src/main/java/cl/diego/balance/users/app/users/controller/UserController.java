package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping( "/user" )
public class UserController {

    private final UserService userService;

    @PostMapping( "/create" )
    public ResponseEntity<Boolean> createUser( @RequestBody User user ) {
        log.info( "UserController.createUser - body: <{}>", user );
        userService.saveUser( user );
        return ResponseEntity.ok( ).build( );
    }

    @GetMapping( "/rut/{rut}" )
    public ResponseEntity<User> getUser( @PathVariable String rut ) {
        log.info( "UserController.getUser - rut: <{}>", rut );
        User userFound = userService.getUserByRut( rut );
        return new ResponseEntity<>( userFound, HttpStatus.OK );
    }

    @PutMapping( "/update" )
    public ResponseEntity<User> updateUser( @RequestBody User user ) {
        log.info( "UserController.updateUser - body: <{}>", user );
        userService.updateUser( user );
        return ResponseEntity.ok( ).build( );
    }

    @DeleteMapping( "/id/{id}" )
    public ResponseEntity<User> deleteUser( @PathVariable Long id ) {
        log.info( "UserController.deleteUser - id: <{}>", id );
        userService.deleteUser( id );
        return ResponseEntity.ok( ).build( );
    }
}


