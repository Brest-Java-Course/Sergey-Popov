package com.epam.brest.courses.dao.impl;

import com.epam.brest.courses.dao.PersonDao;
import com.epam.brest.courses.domain.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by beast on 19.11.14. At 11.58
 */
@Repository
public class PersonDaoImpl implements PersonDao {

    @PersistenceContext
    protected EntityManager emf;

    private static final Logger LOGGER = LogManager.getLogger();

    //@Value("#{T(org.apache.commons.io.IOUtils).toString((new org.springframework.core.io.ClassPathResource('${select_all_persons_path}')).inputStream)}")
    //private String selectAllPersonsSql;

    @Autowired
    private DataSource dataSource;

    @Override
    @Transactional
    public Person addPerson(Person person) {

        LOGGER.debug("addPerson({})", person);

        Assert.notNull(person);
        Assert.isNull(person.getPersonId(), "Person Id should not be specified!");
        Assert.notNull(person.getPersonFirstName(), "Person First Name should be specified!");
        Assert.notNull(person.getPersonLastName(), "Person Last Name should be specified!");

        Person personFromDb = emf.merge(person);
        LOGGER.debug("addPerson() : id = {}", personFromDb.getPersonId());

        return personFromDb;

    }

    @Override
    @Transactional
    public List<Person> getPersons() {

        LOGGER.debug("getPersons()");

        List<Person> persons;
        TypedQuery<Person> query = emf.createNamedQuery("Person.findAll", Person.class);
        persons = query.getResultList();

        LOGGER.debug("getPersons() : list.size = {}", persons.size());
        return persons;

    }

    @Override
    @Transactional
    public Person getPersonById(Long personId) {

        LOGGER.debug("getPersonById(personId = {})", personId);

        Person personFromDb = emf.find(Person.class, personId);

        LOGGER.debug("getPersonById() : person = {}", personFromDb);
        return personFromDb;

    }

    @Override
    @Transactional
    public void updatePerson(Person person) {

        LOGGER.debug("updatePerson({})", person);

        Person personUpdate = emf.find(Person.class, person.getPersonId());
        personUpdate.setPersonFirstName(person.getPersonFirstName());
        personUpdate.setPersonLastName(person.getPersonLastName());

        LOGGER.debug("updatePerson() : personUpdated = {}", personUpdate);

    }

    @Override
    @Transactional
    public void removePerson(Long personId) {

        LOGGER.debug("removePerson(personId = {})", personId);

        Person personFromDb = emf.find(Person.class, personId);
        emf.remove(emf.contains(personFromDb) ? personFromDb : emf.merge(personFromDb));

        LOGGER.debug("removePerson() : personRemoved = {}", personFromDb);

    }

}
