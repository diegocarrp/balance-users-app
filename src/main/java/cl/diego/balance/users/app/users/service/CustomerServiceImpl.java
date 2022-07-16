package cl.diego.balance.users.app.users.service;

import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.BadInputException;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;
import cl.diego.balance.users.app.users.repository.CustomerRepository;
import cl.diego.balance.users.app.users.repository.domain.Customer;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final Validator          validator;

    public CustomerServiceImpl( CustomerRepository customerRepository ) {
        this.customerRepository = customerRepository;
        this.validator          = Validation.byDefaultProvider( )
                .configure( )
                .messageInterpolator( new ParameterMessageInterpolator( ) )
                .buildValidatorFactory( )
                .getValidator( );
    }

    @Override
    public void saveCustomer( CustomerDto customer ) throws BadInputException {
        validateCustomer( customer );
        customerRepository.save( new Customer( customer ) );
    }

    @Override
    public CustomerDto getCustomerByRut( String rut ) {
        Customer customerDb = customerRepository.findByRut( rut )
                .orElseThrow( ( ) -> new CustomerNotFoundException( rut ) );
        log.info( "customerFound: <{}>", customerDb );
        return customerDb.toCustomer( );
    }

    @Override
    public void updateCustomer( CustomerDto customer ) throws BadInputException {
        CustomerDto customerDb = getCustomerByRut( customer.getRut( ) );
        customerDb.updateCustomer(customer);
        customerRepository.save( new Customer( customerDb ) );
    }

    @Override
    public void deleteCustomer( Long id ) {
        customerRepository.deleteById( id );
    }

    private void validateCustomer( CustomerDto customer ) {
        Set<ConstraintViolation<CustomerDto>> violations = validator.validate( customer );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        if( !descriptions.isEmpty( ) ) {
            throw new ApiValidationException( "Customer wasn't created because of: ", descriptions );
        }
    }
}
