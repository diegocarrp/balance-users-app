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

}
