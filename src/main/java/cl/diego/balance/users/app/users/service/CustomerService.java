package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;

public interface CustomerService {

    void saveCustomer( CustomerDto person ) throws BadInputException;

    CustomerDto getCustomerByRut( String rut ) throws CustomerNotFoundException;

    void updateCustomer( CustomerDto person ) throws BadInputException;

    void deleteCustomer( Long id );
}
