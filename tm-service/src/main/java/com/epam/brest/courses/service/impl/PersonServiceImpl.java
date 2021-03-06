package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import com.epam.brest.courses.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by beast on 21.11.14. At 11.27
 */
@Service
public class PersonServiceImpl implements PersonService {

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private PersonDao personDao;

    /**
     *
     * @param person person to be inserted to the database
     * @return personFromDb
     */
    @Override
    public final Person addPerson(final Person person) {

        LOGGER.debug("PersonServiceImpl.addPerson({})", person);

        Assert.notNull(person);
        Assert.isNull(person.getPersonId(),
                "Person Id should not be specified!");
        Assert.notNull(person.getPersonFirstName(),
                "Person First Name should be specified!");
        Assert.notNull(person.getPersonLastName(),
                "Person Last Name should be specified!");

        Person personFromDb = personDao.addPerson(person);
        LOGGER.debug("PersonServiceImpl.addPerson() : id = {}",
                personFromDb.getPersonId());

        return personFromDb;

    }

    /**
     *
     * @return persons
     */
    @Override
    public final List<Person> getPersons() {

        LOGGER.debug("PersonServiceImpl.getPersons()");

        List<Person> persons;
        persons = personDao.getPersons();

        LOGGER.debug("PersonServiceImpl.getPersons() : list.size = {}",
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

        LOGGER.debug("PersonServiceImpl.getPersonsWithTasksBetweenDate()");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("startDate : " + startDate
                    + ", endDate : " + endDate);
        }

        List<Report> persons;
        persons = personDao.getPersonsWithTasksBetweenDate(startDate, endDate);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(
                    "PersonServiceImpl.getPersonsWithTasksBetweenDate() : "
                            + "list.size = {}",
                    persons.size());
        }

        return persons;

    }

    /**
     *
     * @param personId id of the person to return
     * @return personFromDb
     */
    @Override
    public final Person getPersonById(final Long personId) {

        LOGGER.debug("PersonServiceImpl.getPersonById(personId = {})",
                personId);

        Assert.notNull(personId, "Person Id should be specified!");
        Person personFromDb = personDao.getPersonById(personId);

        LOGGER.debug("PersonServiceImpl.getPersonById() : person = {}",
                personFromDb);
        return personFromDb;

    }

    /**
     *
     * @param person person to be stored in the database
     */
    @Override
    public final void updatePerson(final Person person) {

        LOGGER.debug("PersonServiceImpl.updatePerson({})", person);

        Assert.notNull(person);

        personDao.updatePerson(person);

        LOGGER.debug("PersonServiceImpl.updatePerson() : personUpdated = {}",
                person);

    }

    /**
     *
     * @param personId id of the person to be removed
     */
    @Override
    public final void removePerson(final Long personId) {

        LOGGER.debug("PersonServiceImpl.removePerson(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        personDao.removePerson(personId);

        LOGGER.debug("PersonServiceImpl.removePerson() : id personRemoved = {}",
                personId);

    }

}
