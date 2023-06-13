package cl.diego.balance.users.app.users.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CustomerAlreadyRegisteredException extends ApiException {

    public CustomerAlreadyRegisteredException () {
        super( HttpStatus.CONFLICT, "This customer is already registered,");
    }

}
