package cl.diego.balance.users.app.users.service;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.commons.rest.exception.ApiValidationException;
import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;
import cl.diego.balance.users.app.users.repository.mongodb.CustomerMongoRepository;
import cl.diego.balance.users.app.users.repository.mongodb.domain.Customer;
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

    private final CustomerMongoRepository customerRepository;
    private final Validator          validator;

    public CustomerServiceImpl( CustomerMongoRepository customerRepository ) {
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
        Customer customerDb = customerRepository.findByRut( rut );
        log.info( "customerFound: <{}>", customerDb );
        return customerDb.toCustomer( );
    }

    @Override
    public void updateCustomer( CustomerDto customer ) throws BadInputException {
        CustomerDto customerDb = getCustomerByRut( customer.getRut( ) );
        customerDb.updateCustomer( customer );
        customerRepository.save( new Customer( customerDb ) );
    }

    @Override
    public void deleteCustomer( Long id ) {
        log.info( "" );
        customerRepository.deleteById( id.toString() );
    }

    @Override
    public CustomerDto getCustomerById( Long id ) throws CustomerNotFoundException {
        Customer customerDb = customerRepository.findById( id.toString() )
                .orElseThrow( ( ) -> new CustomerNotFoundException( String.valueOf( id ) ) );
        log.info( "customerFound: <{}>", customerDb );
        return customerDb.toCustomer( );
    }

    private void validateCustomer( CustomerDto customer ) {
        Set<ConstraintViolation<CustomerDto>> violations = validator.validate( customer );
        List<String> descriptions = violations.stream( )
                .map( v -> v.getPropertyPath( ) + " - " + v.getMessage( ) )
                .collect( Collectors.toList( ) );

        if( !descriptions.isEmpty( ) ) {
            log.error( "Validation erros creating user: {}", descriptions );
            throw new ApiValidationException( "Customer wasn't created because of: ", descriptions );
        }
    }
}
