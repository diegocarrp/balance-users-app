package cl.diego.balance.users.app.users.service;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;
import cl.diego.balance.users.app.users.repository.mongodb.CustomerMongoRepository;
import cl.diego.balance.users.app.users.repository.mongodb.domain.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    @Mock
    private CustomerMongoRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp( ) {
        customerRepository = mock( CustomerMongoRepository.class );
        customerService    = new CustomerServiceImpl( customerRepository );
    }

    @Test
    void saveCustomerTest_ok( ) {
        // Prepare data
        CustomerDto customer = getCustomerDto( );

        // Set environment
        when( customerRepository.save( any( Customer.class ) ) )
                .thenReturn( new Customer( ) );

        //Execute
        //Assertions
        assertDoesNotThrow( ( ) -> customerService.saveCustomer( customer ) );

        // Verify
        verify( customerRepository ).save( any( Customer.class ) );
    }

    @Test
    void getCustomerByRutTest_ok( ) {
        // Prepare data
        Customer customer = getCustomer( );

        // Set environment
        when( customerRepository.findByRut( anyString( ) ) )
                .thenReturn( customer );

        // Execute
        CustomerDto customerDto = customerService.getCustomerByRut( "1-1" );

        // Assertions
        assertNotNull( customerDto );
        assertEquals( "1-1", customerDto.getRut( ) );
        assertEquals( "Tommy", customerDto.getNames( ) );

        // Verify
        customerRepository.findByRut( anyString( ) );
    }

    @Test
    void getCustomerByRutTest_error( ) {
        // Prepare data

        // Set environment
        when( customerRepository.findByRut( anyString( ) ) )
                .thenReturn( null );

        // Execute
        // Assertions
        assertThrows( CustomerNotFoundException.class, ( ) -> customerService.getCustomerByRut( "1-1" ) );

        // Verify
        customerRepository.findByRut( anyString( ) );
    }

    @Test
    void updateCustomerTest_ok( ) {
        // Prepare data
        Customer customer = getCustomer();

        CustomerDto customerDto = getCustomerDto();

        // Set environment
        when( customerRepository.findByRut( "1-1" ) )
                .thenReturn( customer );
        when( customerRepository.save( any( Customer.class ) ) )
                .thenReturn( customer );

        // Execute
        customerService.updateCustomer( customerDto );

        //Assertions

        // Verify
        verify( customerRepository ).save( any( Customer.class ) );
    }

    @Test
    void deleteCustomerTest_ok( ) {
        // Prepare data

        // Set environment
        doNothing( ).when( customerRepository ).deleteById( anyString( ) );

        // Execute
        customerService.deleteCustomer( 1L );

        //Assertions

        // Verify
        verify( customerRepository ).deleteById( anyString( ) );
    }

    private static CustomerDto getCustomerDto( ) {
        return CustomerDto.builder( )
                .id( "1" )
                .rut( "1-1" )
                .names( "Tommy" )
                .lastname1( "Gonzalez" )
                .lastname2( "Carreno" )
                .email( "tommy@carreno.cl" )
                .cellphone( "983714551" )
                .build( );
    }

    private static Customer getCustomer( ) {
        return Customer.builder( )
                .id( "1" )
                .rut( "1-1" )
                .names( "Tommy" )
                .lastname1( "Gonzalez" )
                .lastname2( "Carreno" )
                .email( "tommy@carreno.cl" )
                .cellphone( "983714551" )
                .build( );
    }

}