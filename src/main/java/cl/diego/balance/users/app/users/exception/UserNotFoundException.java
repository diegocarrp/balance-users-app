package cl.diego.balance.users.app.users.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends ApiException {
    public UserNotFoundException( ) {
        super( HttpStatus.NOT_FOUND, "User wasn't found." );
    }
}
