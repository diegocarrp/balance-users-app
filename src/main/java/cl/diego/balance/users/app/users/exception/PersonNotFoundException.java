package cl.diego.balance.users.app.users.exception;

import cl.diego.balance.commons.rest.exception.ApiException;
import org.springframework.http.HttpStatus;

public class PersonNotFoundException extends ApiException {
    public PersonNotFoundException( String rut ) {
        super( HttpStatus.NOT_FOUND, "Person with RUT: " + rut + " wasn't found." );
    }
}
