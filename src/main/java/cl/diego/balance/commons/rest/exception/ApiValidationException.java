package cl.diego.balance.commons.rest.exception;

import cl.diego.balance.commons.rest.ApiSubError;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ApiValidationException extends ApiException {

    private List<ApiSubError> subErrors;

    public ApiValidationException( List<String> descriptions ) {
        super( HttpStatus.INTERNAL_SERVER_ERROR, "Unexpected error" );
        this.subErrors = mapSubErrors( descriptions );
    }

    public ApiValidationException( String message,
                                   List<String> descriptions ) {
        super( HttpStatus.BAD_REQUEST, message );
        this.subErrors = mapSubErrors( descriptions );
    }

    private List<ApiSubError> mapSubErrors( List<String> descriptions ) {
        return descriptions.stream( )
                .map( s -> {
                    String[] fieldMessage = s.split( " - " );
                    return ApiSubError.builder( )
                            .field( fieldMessage[0] )
                            .message( fieldMessage[1] )
                            .build( );
                } )
                .collect( Collectors.toList( ) );
    }
}
