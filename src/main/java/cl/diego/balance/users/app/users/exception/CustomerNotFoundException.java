package cl.diego.balance.users.app.users.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ApiException {
    public CustomerNotFoundException( String identifier, String field ) {
        super( HttpStatus.NOT_FOUND, "Customer with " + field + ": " + identifier + " wasn't found." );
    }
}
