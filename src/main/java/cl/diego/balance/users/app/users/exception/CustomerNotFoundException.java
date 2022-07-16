package cl.diego.balance.users.app.users.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class CustomerNotFoundException extends ApiException {
    public CustomerNotFoundException( String rut ) {
        super( HttpStatus.NOT_FOUND, "Customer with RUT: " + rut + " wasn't found." );
    }
}
