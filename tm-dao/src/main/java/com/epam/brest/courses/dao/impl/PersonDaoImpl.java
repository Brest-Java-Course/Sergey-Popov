package com.epam.brest.courses.dao.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by beast on 19.11.14. At 11.58
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    /**
     *
     */
    public static final int THIRD_ELEMENT = 3;

    /**
     *
     */
    @PersistenceContext
    private EntityManager emf;

    /**
     *
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     *
     */
    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional
    public final Person addPerson(final Person person) {

        LOGGER.debug("PersonDaoImpl.addPerson({})", person);

        Assert.notNull(person);
        Assert.isNull(person.getPersonId(),
                "Person Id should not be specified!");
        Assert.notNull(person.getPersonFirstName(),
                "Person First Name should be specified!");
        Assert.notNull(person.getPersonLastName(),
                "Person Last Name should be specified!");

        Person personFromDb = emf.merge(person);
        LOGGER.debug("PersonDaoImpl.addPerson() : id = {}",
                personFromDb.getPersonId());

        return personFromDb;

    }

    @Override
    @Transactional
    public final List<Person> getPersons() {

        LOGGER.debug("PersonDaoImpl.getPersons()");

        List<Person> persons;
        TypedQuery<Person> query = emf.createNamedQuery("Person.findAll",
                Person.class);
        persons = query.getResultList();

        LOGGER.debug("PersonDaoImpl.getPersons() : list.size = {}",
                persons.size());
        return persons;

    }

    @Override
    public final List<Report> getPersonsWithTasksBetweenDate(
            final DateTime startDate,
            final DateTime endDate) {

        LOGGER.debug("PersonDaoImpl.getPersonsWithTasksBetweenDate()");
        LOGGER.debug("startDate : " + startDate + ", endDate : " + endDate);

        List<Object[]> persons;
        Query query = emf.createQuery(
                "SELECT "
                        + "p.personId, "
                        + "p.personFirstName, "
                        + "p.personLastName, "
                        + "SUM(t.elapsedTime) as Quantity "
                + "FROM Person p "
                + "LEFT JOIN p.taskSet t "
                + "where t.startDate between :startDate and :endDate "
                + "GROUP BY p.personId");
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        persons = query.getResultList();

        LOGGER.debug(
                "PersonDaoImpl.getPersonsWithTasksBetweenDate() : "
                        + "personsList.size = {}",
                persons.size());

        List<Report> reportList = new ArrayList<>();
        Report report;

        for (Object[] object : persons) {
            report = new Report();
            report.setPersonId((Long) object[0]);
            report.setPersonFirstName((String) object[1]);
            report.setPersonLastName((String) object[2]);
            report.setTimeTotal((Long) object[THIRD_ELEMENT]);
            reportList.add(report);
        }

        LOGGER.debug(
                "PersonDaoImpl.getPersonsWithTasksBetweenDate() : "
                        + "reportList.size = {}",
                reportList.size());

        return reportList;

    }

    @Override
    @Transactional
    public final Person getPersonById(final Long personId) {

        LOGGER.debug("PersonDaoImpl.getPersonById(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        Person personFromDb = emf.find(Person.class, personId);

        LOGGER.debug("PersonDaoImpl.getPersonById() : person = {}",
                personFromDb);
        return personFromDb;

    }

    @Override
    @Transactional
    public final void updatePerson(final Person person) {

        LOGGER.debug("PersonDaoImpl.updatePerson({})", person);

        Assert.notNull(person);

        Person personUpdate = emf.find(Person.class, person.getPersonId());
        personUpdate.setPersonFirstName(person.getPersonFirstName());
        personUpdate.setPersonLastName(person.getPersonLastName());

        LOGGER.debug("PersonDaoImpl.updatePerson() : personUpdated = {}",
                personUpdate);

    }

    @Override
    @Transactional
    public final void removePerson(final Long personId) {

        LOGGER.debug("PersonDaoImpl.removePerson(personId = {})", personId);

        Assert.notNull(personId, "Person Id should be specified!");
        Person personFromDb = emf.find(Person.class, personId);
        emf.remove(
                emf.contains(personFromDb)
                        ? personFromDb : emf.merge(personFromDb));

        LOGGER.debug(
                "PersonDaoImpl.removePerson() : personRemoved = {}",
                personFromDb);

    }

}
