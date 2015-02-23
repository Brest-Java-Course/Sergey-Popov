package com.epam.brest.courses.service.impl;

import com.epam.brest.courses.domain.Person;
import com.epam.brest.courses.domain.Report;
import com.epam.brest.courses.service.PersonService;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.junit.Assert.*;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-service-hsql-test.xml"})
@TestExecutionListeners(
        listeners = {
                DependencyInjectionTestExecutionListener.class,
                DirtiesContextTestExecutionListener.class,
                TransactionalTestExecutionListener.class,
                SqlScriptsTestExecutionListener.class
        }
)
public class PersonServiceImplMySQLTest {

    /**
     *
     */
    @Autowired
    private PersonService personService;

    /**
     * AddPerson test
     * @throws Exception
     */
    @Test
    public void testAddPerson() throws Exception {

        List<Person> persons = personService.getPersons();
        int sizeBefore = persons.size();

        Person person = new Person();
        person.setPersonFirstName("testFirstName");
        person.setPersonLastName("testLastName");

        personService.addPerson(person);

        persons = personService.getPersons();

        assertEquals(sizeBefore, persons.size() - 1);

    }

    /**
     * getPersons test
     * @throws Exception
     */
    @Test
    public void testGetPersons() throws Exception {

        List<Person> persons = personService.getPersons();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetPersonsWithTasksBetweenDate() throws Exception {

        List<Report> persons = personService.getPersonsWithTasksBetweenDate(new DateTime(2013, 1, 1, 1, 1, 1, 0), new DateTime(2015, 1, 1, 1, 1, 1, 0));

        assertNotNull(persons);
        assertFalse(persons.isEmpty());

    }

    /**
     * getPersonById test
     * @throws Exception
     */
    @Test
    public void testGetPersonById() throws Exception {

        Person testPersonAdd = new Person();
        testPersonAdd.setPersonFirstName("testGetPersonByIdF");
        testPersonAdd.setPersonLastName("testGetPersonByIdL");

        Long personId = personService.addPerson(testPersonAdd).getPersonId();

        Person testPersonGet = personService.getPersonById(personId);

        assertNotNull(testPersonGet);
        assertNotNull(testPersonGet.getPersonId());
        assertNotNull(testPersonGet.getPersonFirstName());
        assertNotNull(testPersonGet.getPersonLastName());
        assertEquals(testPersonAdd.getPersonFirstName(), testPersonGet.getPersonFirstName());
        assertEquals(testPersonAdd.getPersonLastName(), testPersonGet.getPersonLastName());

    }

    /**
     * updatePerson test
     * @throws Exception
     */
    @Test
    public void testUpdatePerson() throws Exception {

        Person testPersonAdd = new Person();
        testPersonAdd.setPersonFirstName("testUpdatePersonF");
        testPersonAdd.setPersonLastName("testUpdatePersonL");

        Long personId = personService.addPerson(testPersonAdd).getPersonId();

        Person testPersonUpdate = new Person();
        testPersonUpdate.setPersonId(personId);
        testPersonUpdate.setPersonFirstName("testUpdatePersonFUpdated");
        testPersonUpdate.setPersonLastName("testUpdatePersonLUpdated");

        personService.updatePerson(testPersonUpdate);

        Person testPersonGet = personService.getPersonById(personId);

        assertNotNull(testPersonGet);
        assertNotNull(testPersonGet.getPersonId());
        assertNotNull(testPersonGet.getPersonFirstName());
        assertNotNull(testPersonGet.getPersonLastName());
        assertEquals(testPersonUpdate.getPersonId(), testPersonGet.getPersonId());
        assertEquals(testPersonUpdate.getPersonFirstName(), testPersonGet.getPersonFirstName());
        assertEquals(testPersonUpdate.getPersonLastName(), testPersonGet.getPersonLastName());

    }

    /**
     * removePerson test
     * @throws Exception
     */
    @Test
    public void testRemovePerson() throws Exception {

        List<Person> persons = personService.getPersons();
        int sizeBefore = persons.size();

        personService.removePerson(persons.get(persons.size() - 1).getPersonId());

        persons = personService.getPersons();
        int sizeAfter = persons.size();

        assertEquals(sizeBefore - 1, sizeAfter);

    }

}