package cl.diego.balance.users.app.users.controller;

import cl.diego.balance.users.app.users.domain.PersonDto;
import cl.diego.balance.users.app.users.domain.UserDto;
import cl.diego.balance.users.app.users.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
@Slf4j
@AllArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping( "/create" )
    public ResponseEntity<Boolean> createPerson( @RequestBody PersonDto person ) {
        log.info( "PersonController.createPerson - body: <{}>", person );
        personService.savePerson( person );
        return ResponseEntity.ok( ).build( );
    }

    @GetMapping( "/rut/{rut}" )
    public ResponseEntity<UserDto> getPerson( @PathVariable String rut ) {
        log.info( "PersonController.getPerson - rut: <{}>", rut );
        UserDto userFound = personService.getPersonByRut( rut );
        return new ResponseEntity<>( userFound, HttpStatus.OK );
    }

    @PutMapping( "/update" )
    public ResponseEntity<UserDto> updatePerson( @RequestBody PersonDto person ) {
        log.info( "PersonController.updatePerson - body: <{}>", person );
        personService.updatePerson( person );
        return ResponseEntity.ok( ).build( );
    }

    @DeleteMapping( "/id/{id}" )
    public ResponseEntity<UserDto> deletePerson( @PathVariable Long id ) {
        log.info( "PersonController.deletePerson - id: <{}>", id );
        personService.deletePerson( id );
        return ResponseEntity.ok( ).build( );
    }

}
