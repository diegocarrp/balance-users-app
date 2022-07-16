package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.dto.CustomerDto;
import cl.diego.balance.users.app.users.dto.UserDto;
import cl.diego.balance.users.app.users.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info( "CustomerController.createCustomer - body: <{}>", customer );
        customerService.saveCustomer( customer );
        return ResponseEntity.ok( ).build( );
    }

    @GetMapping( "/rut/{rut}" )
    public ResponseEntity<CustomerDto> getCustomer( @PathVariable String rut ) {
        log.info( "CustomerController.getCustomer - rut: <{}>", rut );
        CustomerDto customerFound = customerService.getCustomerByRut( rut );
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
        log.info( "PersonController.deletePerson - id: <{}>", id );
        customerService.deleteCustomer( id );
        return ResponseEntity.ok( ).build( );
    }

}
