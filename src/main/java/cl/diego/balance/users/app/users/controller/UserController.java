package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping( "/user" )
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    public ResponseEntity<Boolean> createUser( User user ) {

        HttpStatus responseStatus = userService.saveUser( user ) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        return new ResponseEntity( true, responseStatus );

    }

    public ResponseEntity<User> getUser( String userId ) {
        User userFound = userService.getUserByDni( userId );
        return new ResponseEntity<>( userFound, HttpStatus.OK );
    }
}
