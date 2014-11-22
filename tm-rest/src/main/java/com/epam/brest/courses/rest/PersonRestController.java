package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by beast on 22.11.14. At 13.06
 */
@Controller
@RequestMapping(value = "/rest/persons")
public class PersonRestController {

    @Autowired
    private PersonService personService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

        Person personFromDb = personService.addPerson(person);
        return new ResponseEntity<>(personFromDb, HttpStatus.CREATED);

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getPersons() {

        List<Person> persons = personService.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {

        try {

            Person personFromDb = personService.getPersonById(id);
            return new ResponseEntity<>(personFromDb, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity("Person not found for id = " + id + " error: "
                    + e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {

        personService.updatePerson(person);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removePerson(@PathVariable Long id) {

        personService.removePerson(id);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

}