package cl.diego.balance.users.app.users.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;

public interface CustomerService {

    String saveCustomer( CustomerDto person ) throws BadInputException;

    CustomerDto getCustomerByRut( String rut ) throws CustomerNotFoundException;

    void updateCustomer( CustomerDto person ) throws BadInputException;

    void deleteCustomer( Long id );

    CustomerDto getCustomerById( String id ) throws CustomerNotFoundException;
}
