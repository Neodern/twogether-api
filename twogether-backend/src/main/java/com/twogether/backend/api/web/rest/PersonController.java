package com.twogether.backend.api.web.rest;

import java.util.List;

import com.twogether.backend.api.domain.user.Person;
import com.twogether.backend.api.domain.util.CreatedObjectResponse;
import com.twogether.backend.api.repository.user.PersonRepository;
import com.twogether.backend.api.service.util.FormatErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("${api.base.url}/person")
public class PersonController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private FormatErrorService formatErrorService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ApiOperation(value = "Voir tous les utilisateurs")
    public ResponseEntity<List<Person>> findAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Voir un user.")
    public ResponseEntity<Person> find(@PathVariable Long id) {
        Person person = repository.findOne(id);

        if (person == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "utilisateur créé ou modifié avec succès"),
            @ApiResponse(code = 409, message = "l'utilisateur existe déjà en base (violation d'une contrainte d'unicité)")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CreatedObjectResponse> createUser(@RequestBody Person person) {
        try {
            person = repository.save(person);
            return new ResponseEntity<>(new CreatedObjectResponse(person.getId(), person.getVersion()), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e ) {
            String errorMessage = formatErrorService.convertExceptionToErrorMessage(e);

            return new ResponseEntity<>(new CreatedObjectResponse(errorMessage), HttpStatus.CONFLICT);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "liste des personnes trouvées.")
    })
    @RequestMapping(value = "/fuzzy-search", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> fuzzySearch(@RequestParam(name = "criteria") String criteria) {

        List<Person> persons = repository.findByFirstnameContainingIgnoreCase(criteria);

        return new ResponseEntity<>(persons, HttpStatus.OK);
    }


}