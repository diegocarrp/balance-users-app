package cl.diego.balance.users.app.clients.domain;

import cl.diego.balance.users.app.users.domain.PersonDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends PersonDto {

    private String address;

}
