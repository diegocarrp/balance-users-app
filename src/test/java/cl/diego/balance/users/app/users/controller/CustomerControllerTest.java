package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.commons.rest.domain.BadInputException;
import cl.diego.balance.commons.rest.exception.ApiException;
import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.exception.CustomerNotFoundException;
import cl.diego.balance.users.app.users.service.CustomerService;
import cl.diego.balance.users.app.users.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    private CustomerController customerController;
    @Mock
    private CustomerService    customerService;

    @BeforeEach
    void setUp( ) {
        customerService    = mock( CustomerServiceImpl.class );
        customerController = new CustomerController( customerService );
    }

    @Test
    void createCustomerTest_ok( ) {

        // Prepare data
        CustomerDto customer = getCustomer( );

        // Set environment
        when( customerService.saveCustomer( customer ) )
                .thenReturn( "1" );

        // Execute
        ResponseEntity response = customerController.createCustomer( customer );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );

        // Verify
        verify( customerService, times( 1 ) )
                .saveCustomer( any( CustomerDto.class ) );
    }

    @Test
    void createCustomerTest_error( ) {

        // Prepare data
        CustomerDto customer = getCustomer( );

        // Set environment
        doThrow( new BadInputException( ) ).when( customerService ).saveCustomer( customer );

        // Execution
        // Assertions
        assertThrows( ApiException.class, ( ) -> customerController.createCustomer( customer ) );

        // Verify
        verify( customerService, times( 1 ) )
                .saveCustomer( any( CustomerDto.class ) );
    }

    @Test
    void getCustomerTest_ok( ) {

        // Prepare data
        CustomerDto customer = getCustomer( );

        // Set environment
        when( customerService.getCustomerByRut( anyString( ) ) )
                .thenReturn( customer );

        // Execute
        ResponseEntity response = customerController.getCustomerByRut( "1-1" );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );

        // Verify
        verify( customerService, times( 1 ) )
                .getCustomerByRut( anyString( ) );
    }

    @Test
    void getCustomerTest_error( ) {

        // Prepare data
        // Set environment
        when( customerService.getCustomerByRut( anyString( ) ) )
                .thenThrow( new CustomerNotFoundException( "1-1", "RUT" ) );

        // Execute
        // Assertions
        assertThrows( CustomerNotFoundException.class, () -> customerController.getCustomerByRut( "1-1" ) );

        // Verify
        verify( customerService, times( 1 ) )
                .getCustomerByRut( anyString( ) );
    }

    @Test
    void getCustomerByIdTest_ok( ) {

        // Prepare data
        CustomerDto customer = getCustomer( );

        // Set environment
        when( customerService.getCustomerById( anyString() ) )
                .thenReturn( customer );

        // Execute
        ResponseEntity response = customerController.getCustomerById( "1"  );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );

        // Verify
        verify( customerService, times( 1 ) )
                .getCustomerById( anyString() );
    }

    @Test
    void getCustomerByIdTest_error( ) {

        // Prepare data
        // Set environment
        when( customerService.getCustomerById( anyString() ) )
                .thenThrow( new CustomerNotFoundException( "1-1", "RUT" ) );

        // Execute
        // Assertions
        assertThrows( CustomerNotFoundException.class, () -> customerController.getCustomerById( "1" ) );

        // Verify
        verify( customerService, times( 1 ) )
                .getCustomerById( anyString() );
    }

    @Test
    void updateCustomerTest_ok( ) {

        // Prepare Data
        CustomerDto customer = getCustomer();

        // Set Environment
        doNothing( ).when( customerService ).updateCustomer( customer );

        // Execute
        ResponseEntity reponse = customerController.updateCustomer( customer );

        // Assertions
        assertEquals( HttpStatus.OK, reponse.getStatusCode( ) );

        // Verify
        verify( customerService, times( 1 ) )
                .updateCustomer( any( CustomerDto.class ) );
    }

    @Test
    void updateCustomerTest_error( ) {

        // Prepare Data
        CustomerDto customer = getCustomer();

        // Set Environment
        doThrow( new BadInputException( ) ).when( customerService ).updateCustomer( customer );

        // Execute
        // Assertions
        assertThrows( ApiException.class, ( ) -> customerController.updateCustomer( customer ) );

        // Verify
        verify( customerService, times( 1 ) )
                .updateCustomer( any( CustomerDto.class ) );
    }

    @Test
    void deleteCustomerTest_ok( ) {

        // Prepare data
        // Set environment
        doNothing( ).when( customerService ).deleteCustomer( anyLong( ) );

        // Execute
        ResponseEntity response = customerController.deleteCustomer( 1L );

        // Assertions
        assertEquals( HttpStatus.OK, response.getStatusCode( ) );

        // Verify
        verify( customerService, times( 1 ) )
                .deleteCustomer( anyLong( ) );
    }

    @Test
    void deleteCustomerTest_error( ) {

        // Prepare data

        // Set environment
        doThrow( new CustomerNotFoundException( "1-1", "RUT" ) ).when( customerService ).deleteCustomer( anyLong( ) );

        // Execute
        // Assertions
        assertThrows( ApiException.class, ( ) -> customerService.deleteCustomer( 1L ) );

        // Verify
        verify( customerService, times( 1 ) )
                .deleteCustomer( anyLong( ) );
    }

    private static CustomerDto getCustomer( ) {
        return CustomerDto.builder( )
                .id( "1" )
                .rut( "1-1" )
                .names( "Tommy" )
                .lastname1( "Carreno" )
                .lastname2( "Gonzalez" )
                .cellphone( "+569" )
                .email( "¨@gmail.com" )
                .build( );
    }
}