package cl.diego.balance.users.app.users.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL )
public class UserDto extends CustomerDto {

    @NotBlank
    private String password;

    @NotNull
    private RoleDto role;

    public UserDto( String names,
                    String rut,
                    String lastname1,
                    String lastname2,
                    String email,
                    String cellphone,
                    String password,
                    RoleDto role ) {
        super( rut, email, names, lastname1, lastname2, cellphone );
        this.password = password;
        this.role     = role;
    }

    public UserDto( String names,
                    String rut,
                    String lastname1,
                    String email,
                    String cellphone,
                    String password,
                    RoleDto role ) {
        super( rut, email, names, lastname1, cellphone );
        this.password = password;
        this.role     = role;
    }

    @Override
    public String toString( ) {
        return "User{" +
                "id=" + getId( ) +
                ", rut='" + getRut( ) + '\'' +
                ", names='" + getNames( ) + '\'' +
                ", lastname1='" + getLastname1( ) + '\'' +
                ", lastname2='" + getLastname2( ) + '\'' +
                ", email='" + getEmail( ) + '\'' +
                ", cellphone='" + getCellphone( ) + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public void updateUser( UserDto user ) {
        this.setNames( user.getNames( ) );
        this.setLastname1( user.getLastname1( ) );
        this.setLastname2( user.getLastname2( ) );
        this.setEmail( user.getEmail( ) );
        this.setCellphone( user.getCellphone( ) );
        this.setPassword( user.getPassword( ) );
        this.setRole( user.getRole( ) );
    }
}
