package cl.diego.balance.users.app.users.repository;

import cl.diego.balance.users.app.users.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "users" )
@ToString
public class UserEntity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;
    private String rut;
    private String email;
    private String names;
    private String lastname1;
    private String lastname2;
    private String cellphone;
    private String password;
    private String role;

    public UserEntity( User user ) {
        this.rut       = user.getRut( );
        this.id        = user.getId( );
        this.email     = user.getEmail( );
        this.password  = user.getPassword( );
        this.role      = user.getRole( );
        this.names     = user.getNames( );
        this.lastname1 = user.getLastname1( );
        this.lastname2 = user.getLastname2( );
        this.cellphone = user.getCellphone( );
    }

    public User toUser( ) {

        User user = User.builder( )
                .role( this.role )
                .build( );

        user.setId( this.id );
        user.setRut( this.rut );
        user.setNames( this.names );
        user.setLastname1( this.lastname1 );
        user.setLastname2( this.lastname2 );
        user.setEmail( this.email );
        user.setCellphone( this.cellphone );
        user.setNames( this.names );

        return user;
    }

}
