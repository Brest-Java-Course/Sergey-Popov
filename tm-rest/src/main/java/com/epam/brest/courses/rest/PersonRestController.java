package com.epam.brest.courses.rest;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import com.epam.brest.courses.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by beast on 22.11.14. At 13.06
 */
@RestController
@RequestMapping(value = "/rest/persons")
public class PersonRestController {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PersonService personService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {

        Person personFromDb = personService.addPerson(person);
        return new ResponseEntity<>(personFromDb, HttpStatus.CREATED);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Person>> getPersons() {

        List<Person> persons = personService.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);

    }

    @RequestMapping(value = "/tasksBetween/{startDate}/{endDate}/",method = RequestMethod.GET)
    public ResponseEntity<List<Report>> getPersonsWithTasksBetweenDate(@PathVariable String startDate,
                                                                       @PathVariable String endDate) {

        LOGGER.debug("StartDate : " + startDate +
                ", EndDate : " + endDate);
        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
        DateTime dtStart = formatter.parseDateTime(startDate);
        DateTime dtEnd = formatter.parseDateTime(endDate);
        List<Report> persons = personService.getPersonsWithTasksBetweenDate(dtStart, dtEnd);
        return new ResponseEntity<>(persons, HttpStatus.OK);

    }

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

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<String> updatePerson(@RequestBody Person person) {

        personService.updatePerson(person);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removePerson(@PathVariable Long id) {

        personService.removePerson(id);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

}