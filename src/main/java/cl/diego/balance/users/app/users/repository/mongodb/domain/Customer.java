package cl.diego.balance.users.app.users.repository.mongodb.domain;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "customers")
public class Customer {

    @Id
    private String id;
    private String rut;
    private String email;
    private String names;
    private String lastname1;
    private String lastname2;
    private String cellphone;

    public Customer( CustomerDto customer ) {
        this.rut       = customer.getRut( );
        this.id        = customer.getId( );
        this.email     = customer.getEmail( );
        this.names     = customer.getNames( );
        this.lastname1 = customer.getLastname1( );
        this.lastname2 = customer.getLastname2( );
        this.cellphone = customer.getCellphone( );
    }

    public CustomerDto toCustomer( ) {

        return CustomerDto.builder()
                .id( this.id )
                .rut( rut )
                .email( this.email )
                .names( this.names )
                .lastname1( this.lastname1 )
                .lastname2( this.lastname2 )
                .cellphone( this.cellphone )
                .build();
    }

}
