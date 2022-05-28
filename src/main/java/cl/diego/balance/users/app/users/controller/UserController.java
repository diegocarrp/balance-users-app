package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.User;
import cl.diego.balance.users.app.users.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/user" )
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    public boolean createUser( User user ) {
        userService.saveUser( user );
        return true;
    }

    public boolean getUser( String userId ) {
        userService.getUserByDni( userId );
        return true;
    }
}
