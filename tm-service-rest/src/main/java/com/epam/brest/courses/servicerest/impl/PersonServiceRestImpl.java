package com.epam.brest.courses.servicerest.impl;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import com.epam.brest.courses.servicerest.PersonServiceRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by beast on 21.11.14. At 11.27
 */
@Service
public class PersonServiceRestImpl implements PersonServiceRest {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    private static final String REST_URL = "http://localhost:8080/rest/persons";

    /**
     *
     */
    private static final String REST_GET_PERS_BY_DATE =
            "/tasksBetween/{startDate}/{endDate}/";

    /**
     *
     */
    private static final String REST_GET_PERS_BY_ID = "/{id}";

    /**
     *
     */
    private static final String REST_REMOVE_PERS_BY_ID = "/{id}";

    /**
     *
     * @param person person to be inserted to the database
     * @return personFromDb
     */
    @Override
    public final Person addPerson(final Person person) {

        LOGGER.debug("PersonServiceRestImpl.addPerson({})", person);

        Assert.notNull(person);
        Assert.isNull(person.getPersonId(),
                "Person Id should not be specified!");
        Assert.notNull(person.getPersonFirstName(),
                "Person First Name should be specified!");
        Assert.notNull(person.getPersonLastName(),
                "Person Last Name should be specified!");

        RestTemplate restTemplate = new RestTemplate();
        Person personFromDb = restTemplate.postForObject(
                REST_URL,
                person,
                Person.class);
        LOGGER.debug("PersonServiceRestImpl.addPerson() : id = {}",
                personFromDb.getPersonId());

        return personFromDb;

    }

    /**
     *
     * @return persons
     */
    @Override
    public final List<Person> getPersons() {

        LOGGER.debug("PersonServiceRestImpl.getPersons()");

        RestTemplate restTemplate = new RestTemplate();
        Person[] personList = restTemplate.getForObject(
                REST_URL,
                Person[].class);
        List<Person> persons = Arrays.asList(personList);

        LOGGER.debug("PersonServiceRestImpl.getPersons() : list.size = {}",
                persons.size());

        return persons;

    }

    /**
     *
     * @param startDate start date
     * @param endDate end date
     * @return persons
     */
    @Override
    public final List<Report> getPersonsWithTasksBetweenDate(
            final DateTime startDate,
            final DateTime endDate) {

        LOGGER.debug("PersonServiceRestImpl.getPersons()");

        RestTemplate restTemplate = new RestTemplate();

        Report[] personList = restTemplate.getForObject(
                REST_URL + REST_GET_PERS_BY_DATE,
                Report[].class,
                startDate,
                endDate);
        List<Report> persons = Arrays.asList(personList);

        LOGGER.debug("PersonServiceRestImpl.getPersons() : list.size = {}",
                persons.size());

        return persons;

    }

    /**
     *
     * @param personId id of the person to return
     * @return personFromDb
     */
    @Override
    public final Person getPersonById(final Long personId) {

        LOGGER.debug("PersonServiceRestImpl.getPersonById(personId = {})",
                personId);

        Assert.notNull(personId, "Person Id should be specified!");
        RestTemplate restTemplate = new RestTemplate();
        Person personFromDb = restTemplate.getForObject(
                REST_URL + REST_GET_PERS_BY_ID,
                Person.class,
                personId);

        LOGGER.debug("PersonServiceRestImpl.getPersonById() : person = {}",
                personFromDb);
        return personFromDb;

    }

    /**
     *
     * @param person person to be stored in the database
     */
    @Override
    public final void updatePerson(final Person person) {

        LOGGER.debug("PersonServiceRestImpl.updatePerson({})", person);

        Assert.notNull(person);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_URL, person);

        LOGGER.debug(
                "PersonServiceRestImpl.updatePerson() : personUpdated = {}",
                person);

    }

    /**
     *
     * @param personId id of the person to be removed
     */
    @Override
    public final void removePerson(final Long personId) {

        LOGGER.debug("PersonServiceRestImpl.removePerson(personId = {})",
                personId);

        Assert.notNull(personId, "Person Id should be specified!");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_URL + REST_REMOVE_PERS_BY_ID, personId);

        LOGGER.debug(
                "PersonServiceRestImpl.removePerson() : id personRemoved = {}",
                personId);

    }

}
