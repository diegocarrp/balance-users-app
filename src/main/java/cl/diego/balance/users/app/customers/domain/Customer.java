package cl.diego.balance.users.app.customers.domain;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends CustomerDto {

    private String address;

}
