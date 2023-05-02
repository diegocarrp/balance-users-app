package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
@Slf4j
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping( "/create" )
    public ResponseEntity<Boolean> createCustomer( @RequestBody CustomerDto customer ) {
        String trackingId = MDC.get("trackingId");
        log.info( "CustomerController.createCustomer - body: <{}> - tracking <{}>", customer, trackingId );
        customerService.saveCustomer( customer );
        return ResponseEntity.ok( ).build( );
    }

    @GetMapping( "/by-rut/{rut}" )
    public ResponseEntity<CustomerDto> getCustomerByRut( @PathVariable String rut ) {
        log.info( "CustomerController.getCustomer - rut: <{}>", rut );
        CustomerDto customerFound = customerService.getCustomerByRut( rut );
        return new ResponseEntity<>( customerFound, HttpStatus.OK );
    }

    @GetMapping( "/by-id/{id}" )
    public ResponseEntity<CustomerDto> getCustomerById( @PathVariable String id ) {
        log.info( "CustomerController.getCustomer - id: <{}>", id );
        CustomerDto customerFound = customerService.getCustomerById( id );
        return new ResponseEntity<>( customerFound, HttpStatus.OK );
    }

    @PutMapping( "/update" )
    public ResponseEntity<UserDto> updateCustomer( @RequestBody CustomerDto customer ) {
        log.info( "CustomerController.updateCustomer - body: <{}>", customer );
        customerService.updateCustomer( customer );
        return ResponseEntity.ok( ).build( );
    }

    @DeleteMapping( "/id/{id}" )
    public ResponseEntity<UserDto> deleteCustomer( @PathVariable Long id ) {
        log.info( "PersonController.deleteCustomer - id: <{}>", id );
        customerService.deleteCustomer( id );
        return ResponseEntity.ok( ).build( );
    }

}
