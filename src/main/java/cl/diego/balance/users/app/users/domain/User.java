package cl.diego.balance.users.app.users.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude( JsonInclude.Include.NON_NULL)
public class User extends Person {

    private String password;
    private String role;

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

    public void updateUser( User user ) {
        this.setNames( user.getNames( ) );
        this.setLastname1( user.getLastname1( ) );
        this.setLastname2( user.getLastname2( ) );
        this.setEmail( user.getEmail( ) );
        this.setCellphone( user.getCellphone( ) );
        this.setPassword( user.getPassword( ) );
        this.setRole( user.getRole( ) );
    }
}
