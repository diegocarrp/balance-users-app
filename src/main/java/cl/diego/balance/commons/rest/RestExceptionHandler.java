package cl.diego.balance.commons.rest;

import cl.diego.balance.commons.rest.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

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

    private ResponseEntity<ApiError> buildResponseEntity( ApiError apiError ) {
        return new ResponseEntity<>( apiError, apiError.getStatus() );
    }
}
