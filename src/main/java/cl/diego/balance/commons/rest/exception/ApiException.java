package cl.diego.balance.commons.rest.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode( callSuper = true )
@Data
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private HttpStatus status;
    private String     message;
}

