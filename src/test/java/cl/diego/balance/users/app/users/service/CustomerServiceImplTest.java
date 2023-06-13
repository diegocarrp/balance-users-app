package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;
import cl.diego.balance.users.app.users.repository.mongodb.CustomerMongoRepository;
import cl.diego.balance.users.app.users.repository.mongodb.domain.Customer;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static cl.diego.balance.commons.testing.UtilForTesting.getMappedObjectFromFile;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@Slf4j
@ExtendWith( SpringExtension.class )
@ContextConfiguration( classes = CustomerServiceImplTest.TestConfiguration.class )
class CustomerServiceImplTest {

    static class TestConfiguration {
        @Bean
        CustomerService customerService(
                final CustomerMongoRepository customerMongoRepository ) {
            Validator validator = mock( Validator.class );
            return new CustomerServiceImpl( customerMongoRepository, validator );
        }

    }

    @Autowired
    private CustomerService         customerService;
    @MockBean
    private CustomerMongoRepository customerRepository;

    private static Customer    customerOne;
    private static CustomerDto customerOneDto;

    private static final String CUSTOMER_ONE = "customerOne.json";

    @BeforeAll
    static void setUp( ) throws IOException {
        customerOne    = getMappedObjectFromFile( CUSTOMER_ONE, Customer.class );
        customerOneDto = getMappedObjectFromFile( CUSTOMER_ONE, CustomerDto.class );

        log.info( "Customer One: {}", customerOne );
        log.info( "Customer One Dto: {}", customerOneDto );
    }

    @Test
    void saveCustomerTest_ok( ) {
        when( customerRepository.save( customerOne ) )
                .thenReturn( new Customer( ) );

        assertDoesNotThrow( ( ) -> customerService.saveCustomer( customerOneDto ) );

        verify( customerRepository ).save( any( Customer.class ) );
    }

    @Test
    void getCustomerByRutTest_ok( ) {
        when( customerRepository.findByRut( customerOneDto.getRut( ) ) )
                .thenReturn( customerOne );

        CustomerDto customerDto = customerService.getCustomerByRut( customerOneDto.getRut( ) );

        assertNotNull( customerDto );
        assertEquals( "1-1", customerDto.getRut( ) );
        assertEquals( "Tommy", customerDto.getNames( ) );

        // Verify
        verify( customerRepository ).findByRut( anyString( ) );
    }

    @Test
    void getCustomerByRutTest_error( ) {
        when( customerRepository.findByRut( anyString( ) ) )
                .thenReturn( null );

        assertThrows( CustomerNotFoundException.class, ( ) -> customerService.getCustomerByRut( "1-1" ) );
    }

    @Test
    void updateCustomerTest_ok( ) {
        when( customerRepository.findByRut( customerOneDto.getRut( ) ) )
                .thenReturn( customerOne );
        when( customerRepository.save( customerOne ) )
                .thenReturn( customerOne );

        customerService.updateCustomer( customerOneDto );

        verify( customerRepository ).findByRut( "1-1" );
        verify( customerRepository ).save( any( Customer.class ) );
    }

    @Test
    void deleteCustomerTest_ok( ) {
        doNothing( ).when( customerRepository ).deleteById( anyString( ) );

        customerService.deleteCustomer( 1L );

        verify( customerRepository ).deleteById( anyString( ) );
    }

}