package cl.diego.balance.users.app.users.repository.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "customer" )
@ToString
public class Customer {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;
    private String rut;
    private String email;
    private String names;
    private String lastname1;
    private String lastname2;
    private String cellphone;

}
