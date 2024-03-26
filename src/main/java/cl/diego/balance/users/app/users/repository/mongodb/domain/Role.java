package cl.diego.balance.users.app.users.repository.mongodb.domain;

import cl.diego.balance.users.app.users.dto.RoleDto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "roles")
public class Role {

    @Id
    private String id;
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
