package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/user" )
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser( User user ) {

        userService.saveUser( user );
        return ResponseEntity.ok().build();

    }

    @GetMapping("/id/{userId}")
    public ResponseEntity<User> getUser( String userId ) {
        User userFound = userService.getUserByDni( userId );
        return new ResponseEntity<>( userFound, HttpStatus.OK );
    }
}
