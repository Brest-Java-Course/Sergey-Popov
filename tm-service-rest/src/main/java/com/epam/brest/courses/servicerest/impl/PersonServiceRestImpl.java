package com.epam.brest.courses.servicerest.impl;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.servicerest.PersonServiceRest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOGGER = LogManager.getLogger();

    private static final String REST_URL = "http://localhost:8080/rest/persons";
    private static final String REST_GET_PERSON_BY_ID = "/{id}";
    private static final String REST_REMOVE_PERSON_BY_ID = "/{id}";

    @Override
    public Person addPerson(Person person) {

        LOGGER.debug("PersonServiceImpl.addPerson({})", person);

        Assert.notNull(person);
        Assert.isNull(person.getPersonId(), "Person Id should not be specified!");
        Assert.notNull(person.getPersonFirstName(), "Person First Name should be specified!");
        Assert.notNull(person.getPersonLastName(), "Person Last Name should be specified!");

        RestTemplate restTemplate = new RestTemplate();
        Person personFromDb = restTemplate.postForObject(REST_URL, person, Person.class);
        LOGGER.debug("PersonServiceImpl.addPerson() : id = {}", personFromDb.getPersonId());

        return personFromDb;

    }

    @Override
    public List<Person> getPersons() {

        LOGGER.debug("PersonServiceImpl.getPersons()");

        RestTemplate restTemplate = new RestTemplate();
        Person[] personList = restTemplate.getForObject(REST_URL, Person[].class);
        List<Person> persons = Arrays.asList(personList);

        LOGGER.debug("PersonServiceImpl.getPersons() : list.size = {}", persons.size());

        return persons;

    }

    @Override
    public Person getPersonById(Long personId) {

        LOGGER.debug("PersonServiceImpl.getPersonById(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        RestTemplate restTemplate = new RestTemplate();
        Person personFromDb = restTemplate.getForObject(REST_URL + REST_GET_PERSON_BY_ID, Person.class, personId);

        LOGGER.debug("PersonServiceImpl.getPersonById() : person = {}", personFromDb);
        return personFromDb;

    }

    @Override
    public void updatePerson(Person person) {

        LOGGER.debug("PersonServiceImpl.updatePerson({})", person);

        Assert.notNull(person);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(REST_URL, person);

        LOGGER.debug("PersonServiceImpl.updatePerson() : personUpdated = {}", person);

    }

    @Override
    public void removePerson(Long personId) {

        LOGGER.debug("PersonServiceImpl.removePerson(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_URL + REST_REMOVE_PERSON_BY_ID, personId);

        LOGGER.debug("PersonServiceImpl.removePerson() : id personRemoved = {}", personId);

    }

}