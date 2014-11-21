package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by beast on 21.11.14. At 11.27
 */
@Service
public class PersonServiceImpl implements PersonService {

    private static final Logger LOGGER = LogManager.getLogger();

    @Autowired
    private PersonDao personDao;

    @Override
    public Person addPerson(Person person) {

        LOGGER.debug("addPerson({})", person);

        Assert.notNull(person);
        Assert.isNull(person.getPersonId(), "Person Id should not be specified!");
        Assert.notNull(person.getPersonFirstName(), "Person First Name should be specified!");
        Assert.notNull(person.getPersonLastName(), "Person Last Name should be specified!");

        Person personFromDb = personDao.addPerson(person);
        LOGGER.debug("addPerson() : id = {}", personFromDb.getPersonId());

        return personFromDb;

    }

    @Override
    public List<Person> getPersons() {

        LOGGER.debug("getPersons()");

        List<Person> persons;
        persons = personDao.getPersons();

        LOGGER.debug("getPersons() : list.size = {}", persons.size());

        return persons;

    }

    @Override
    public Person getPersonById(Long personId) {

        LOGGER.debug("getPersonById(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        Person personFromDb = personDao.getPersonById(personId);

        LOGGER.debug("getPersonById() : person = {}", personFromDb);
        return personFromDb;

    }

    @Override
    public void updatePerson(Person person) {

        LOGGER.debug("updatePerson({})", person);

        Assert.notNull(person);

        personDao.updatePerson(person);

        LOGGER.debug("updatePerson() : personUpdated = {}", person);

    }

    @Override
    public void removePerson(Long personId) {

        LOGGER.debug("removePerson(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        personDao.removePerson(personId);

        LOGGER.debug("removePerson() : id personRemoved = {}", personId);

    }

}