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

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping( "/user" )
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser( @RequestBody User user ) {
        try {
            log.info( "UserController.createUser: <{}>", user );
            userService.saveUser( user );
            return ResponseEntity.ok().build();
        } catch ( BadInputException e ) {
            log.error( "UserController.createUser: <{}>", e.getMessage(), e );
            return ResponseEntity.status( HttpStatus.BAD_REQUEST ).build();
        }

    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUser( @PathVariable String userId ) {
        try {
            log.info( "UserController.getUser: <{}>", userId );
            User userFound = userService.getUserByDni( userId );
            return new ResponseEntity<>( userFound, HttpStatus.OK );
        } catch ( UserNotFoundException e ) {
            log.error( "UserController.getUser: <{}>", e.getMessage() );
            return new ResponseEntity<>( HttpStatus.NOT_FOUND );
        }
    }
}


