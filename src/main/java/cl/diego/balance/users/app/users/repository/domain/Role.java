package cl.diego.balance.users.app.users.repository.domain;

import cl.diego.balance.users.app.users.dto.RoleDto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table( schema = "public", name = "role" )
@ToString
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long   id;
    private String name;

    public RoleDto toRole( ) {
        return RoleDto.builder( )
                .id( this.id )
                .name( this.name )
                .build( );
    }

    public Role( RoleDto role ) {
        this.id   = role.getId( );
        this.name = role.getName( );
    }
}
