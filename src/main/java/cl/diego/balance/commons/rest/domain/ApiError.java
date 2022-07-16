package cl.diego.balance.commons.rest.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
public class ApiError {

    private HttpStatus    status;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss" )
    private LocalDateTime timestamp;
    private String        message;
    private String        debugMessage;

    ApiError( ) {
        timestamp = LocalDateTime.now( );
    }

    ApiError( HttpStatus status ) {
        this( );
        this.status = status;
    }

    ApiError( HttpStatus status,
              String message ) {
        this( );
        this.status    = status;
        this.message   = message;
        this.timestamp = LocalDateTime.now( );
    }

    ApiError( HttpStatus status,
              Throwable ex ) {
        this( );
        this.status       = status;
        this.message      = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage( );
        this.timestamp    = LocalDateTime.now( );
    }

    ApiError( HttpStatus status,
              String message,
              Throwable ex ) {
        this( );
        this.status       = status;
        this.message      = message;
        this.debugMessage = ex.getLocalizedMessage( );
        this.timestamp    = LocalDateTime.now( );
    }
}
