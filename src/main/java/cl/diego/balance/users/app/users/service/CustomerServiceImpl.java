package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;
import cl.diego.balance.users.app.users.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository personRepository;

    @Override
    public void saveCustomer( CustomerDto person ) throws BadInputException {

    }

    @Override
    public UserDto getCustomerByRut( String rut ) throws CustomerNotFoundException {
        return null;
    }

    @Override
    public void updateCustomer( CustomerDto person ) throws BadInputException {

    }

    @Override
    public void deleteCustomer( Long id ) {

    }
}
