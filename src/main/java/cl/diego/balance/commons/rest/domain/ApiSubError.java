package cl.diego.balance.commons.rest.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = false )
@AllArgsConstructor
@Builder
public class ApiSubError {
    private String field;
    private String message;
}
