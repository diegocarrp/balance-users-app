package cl.diego.balance.commons.rest;

import cl.diego.balance.commons.rest.exception.ApiException;
import cl.diego.balance.commons.rest.exception.ApiValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( ApiException.class)
    protected ResponseEntity<ApiError> handleApiException(
            ApiException ex ) {
        log.info("Api Exception <{}>", ex);
        return buildResponseEntity( ApiError.builder()
                .status( ex.getStatus() )
                .message( ex.getMessage() )
                .build() );
    }

    @ExceptionHandler( ApiValidationException.class)
    protected ResponseEntity<ApiValidationError> handleApiValidationException(
            ApiValidationException ex ) {
        log.info("Api Validation Exception <{}>", ex);
        ApiValidationError validationError = new ApiValidationError( ex );
        return buildResponseEntity( validationError );
    }

    private ResponseEntity<ApiError> buildResponseEntity( ApiError apiError ) {
        return new ResponseEntity<>( apiError, apiError.getStatus() );
    }

    private ResponseEntity<ApiValidationError> buildResponseEntity( ApiValidationError apiValidationError ) {
        return new ResponseEntity<>( apiValidationError, apiValidationError.getStatus() );
    }
}
