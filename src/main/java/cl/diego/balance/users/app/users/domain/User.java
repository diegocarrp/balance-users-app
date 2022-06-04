package cl.diego.balance.users.app.users.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends Person {

    private String password;
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", rut='" + getRut() + '\'' +
                ", names='" + getNames() + '\'' +
                ", lastname1='" + getLastname1() + '\'' +
                ", lastname2='" + getLastname2() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", cellphone='" + getCellphone() + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
