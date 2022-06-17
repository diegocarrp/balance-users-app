package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.UserNotFoundException;
import cl.diego.balance.users.app.users.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping( "/user" )
public class UserController {

    private final UserService userService;

    @PostMapping( "/create" )
    public ResponseEntity<Boolean> createUser( @RequestBody User user ) {
        try {
            log.info( "UserController.createUser - body: <{}>", user );
            userService.saveUser( user );
            return ResponseEntity.ok().build();
        } catch (BadInputException e) {
            log.error( "UserController.createUser: <{}>", e.getMessage(), e );
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, e.getMessage(), e );
        }

    }

    @GetMapping( "/rut/{rut}" )
    public ResponseEntity<User> getUser( @PathVariable String rut ) {
        try {
            log.info( "UserController.getUser - rut: <{}>", rut );
            User userFound = userService.getUserByRut( rut );
            return new ResponseEntity<>( userFound, HttpStatus.OK );
        } catch (UserNotFoundException e) {
            log.error( "UserController.getUser: <{}>", e.getMessage() );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage(), e );
        }
    }

    @PutMapping( "/update" )
    public ResponseEntity<User> updateUser( @RequestBody User user ) {
        try {
            log.info( "UserController.updateUser - body: <{}>", user );
            userService.updateUser( user );
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            log.error( "UserController.updateUser: <{}>", e.getMessage() );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage(), e );
        }
    }

    @DeleteMapping( "/id/{id}" )
    public ResponseEntity<User> deleteUser( @PathVariable Long id ) {
        try {
            log.info( "UserController.deleteUser - id: <{}>", id );
            userService.deleteUser( id );
            return ResponseEntity.ok().build();
        } catch (UserNotFoundException e) {
            log.error( "UserController.deleteUser: <{}>", e.getMessage() );
            throw new ResponseStatusException( HttpStatus.NOT_FOUND, e.getMessage(), e );
        }
    }
}


