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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by beast on 22.11.14. At 13.06
 */
@RestController
@RequestMapping(value = "/rest/persons")
public class PersonRestController {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private PersonService personService;

    /**
     *
     * @param person person
     * @return ResponseEntity<>
     */
    @RequestMapping(method = RequestMethod.POST)
    public final ResponseEntity<Person> addPerson(
             @RequestBody final Person person) {

        Person personFromDb = personService.addPerson(person);
        return new ResponseEntity<>(personFromDb, HttpStatus.CREATED);

    }

    /**
     *
     * @return ResponseEntity<>
     */
    @RequestMapping(method = RequestMethod.GET)
    public final ResponseEntity<List<Person>> getPersons() {

        List<Person> persons = personService.getPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);

    }

    /**
     *
     * @param startDate startDate
     * @param endDate endDate
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/tasksBetween/{startDate}/{endDate}/",
            method = RequestMethod.GET)
    public final ResponseEntity<List<Report>> getPersonsWithTasksBetweenDate(
            @PathVariable final String startDate,
            @PathVariable final String endDate) {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("StartDate : " + startDate
                    + ", EndDate : " + endDate);
        }
        DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
        DateTime dtStart = formatter.parseDateTime(startDate);
        DateTime dtEnd = formatter.parseDateTime(endDate);
        List<Report> persons = personService.getPersonsWithTasksBetweenDate(
                dtStart, dtEnd);
        return new ResponseEntity<>(persons, HttpStatus.OK);

    }

    /**
     *
     * @param id id
     * @return ResponseEntity
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public final ResponseEntity<Person> getPersonById(
            @PathVariable final Long id) {

        try {

            Person personFromDb = personService.getPersonById(id);
            return new ResponseEntity<>(personFromDb, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity("Person not found for id = "
                    + id + " error: "
                    + e.getMessage(), HttpStatus.NOT_FOUND);

        }

    }

    /**
     *
     * @param person person
     * @return ResponseEntity<>
     */
    @RequestMapping(method = RequestMethod.PUT)
    public final ResponseEntity<String> updatePerson(
            @RequestBody final Person person) {

        personService.updatePerson(person);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

    /**
     *
     * @param id id
     * @return ResponseEntity<>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public final ResponseEntity<String> removePerson(
            @PathVariable final Long id) {

        personService.removePerson(id);
        return new ResponseEntity<>("", HttpStatus.OK);

    }

}
