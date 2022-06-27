package cl.diego.balance.users.app.users.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private Long   id;
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

    Person( String rut,
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

    Person( String rut,
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

}
