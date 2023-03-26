package cl.diego.balance.users.app.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class CustomerDto {

    private String id;
    @NotBlank
    private String rut;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String names;
    @NotBlank
    private String lastname1;
    private String lastname2;
    @NotBlank
    private String cellphone;

    CustomerDto( String rut,
                 String email,
                 String names,
                 String lastname1,
                 String lastname2,
                 String cellphone ) {
        this.rut       = rut;
        this.email     = email;
        this.names     = names;
        this.lastname1 = lastname1;
        this.lastname2 = lastname2;
        this.cellphone = cellphone;
    }

    CustomerDto( String rut,
                 String email,
                 String names,
                 String lastname1,
                 String cellphone ) {
        this.rut       = rut;
        this.email     = email;
        this.names     = names;
        this.lastname1 = lastname1;
        this.cellphone = cellphone;
    }

    public void updateCustomer( CustomerDto customer ) {
        this.setNames( customer.getNames( ) );
        this.setLastname1( customer.getLastname1( ) );
        this.setLastname2( customer.getLastname2( ) );
        this.setEmail( customer.getEmail( ) );
        this.setCellphone( customer.getCellphone( ) );
    }
}
