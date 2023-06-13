package cl.diego.balance.users.app.users.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class UserAlreadyRegisteredException extends ApiException {

    public UserAlreadyRegisteredException() {
        super( HttpStatus.CONFLICT, "This user is already registered,");
    }

}
